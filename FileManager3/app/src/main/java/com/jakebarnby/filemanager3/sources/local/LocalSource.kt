package com.jakebarnby.filemanager3.sources.local

import android.content.Context
import android.os.Environment
import android.os.StatFs
import android.util.Log
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile
import com.jakebarnby.filemanager3.sources.models.SourceType
import com.jakebarnby.filemanager3.sources.models.StorageStats
import com.jakebarnby.filemanager3.util.Constants.Sources.LOCAL
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.IOException
import java.util.*

class LocalSource : Source(SourceType.LOCAL, LOCAL) {

    override fun authenticateSource(context: Context): Flowable<Any> =
        Flowable.create({
            it.onComplete()
        }, BackpressureStrategy.BUFFER)

    override fun loadSource(context: Context): Flowable<Any> {
        return Flowable.create({
            val root = Environment.getExternalStorageDirectory()
            val queue = LinkedList<File>()
            val fileList = mutableListOf<SourceFile>()

            var parentId = rootFileId
            var fileCounter = 0
            var setNewIdIn = Int.MAX_VALUE

            queue.offer(root)

            while (!queue.isEmpty()) {
                val file = queue.poll()

                if (fileCounter == setNewIdIn) {
                    parentId = file.hashCode().toLong()
                    setNewIdIn = Int.MAX_VALUE
                }

                val sourceFile = SourceFile(file, parentId)
                fileList.add(sourceFile)
                it.onNext(sourceFile)

                Log.d("File inserted", file.absolutePath)

                if (file.isDirectory) {
                    val initialLength = queue.size
                    file.listFiles().forEach { queue.offer(it) }
                    val newLength = queue.size
                    setNewIdIn = newLength - initialLength
                    fileCounter = -1
                }
                fileCounter++
            }
            fileDao.insertFiles(fileList)
            it.onComplete()
        }, BackpressureStrategy.BUFFER)
    }

    override fun logout(context: Context): Flowable<Any> =
        Flowable.create({
            it.onComplete()
        }, BackpressureStrategy.BUFFER)

    override fun getStorageStats(): Flowable<StorageStats> {
        return Flowable.create({ emitter ->
            if (storageStats == null) {
                val stats = StorageStats()
                disposables.add(
                    fileDao.getFileById(rootFileId)
                        .subscribeOn(Schedulers.io())
                        .subscribe { file ->
                            try {
                                val fsStats = StatFs(file.path)
                                stats.apply {
                                    freeSpace = fsStats.freeBytes
                                    totalSpace = fsStats.totalBytes
                                    usedSpace = totalSpace - freeSpace
                                }
                                storageStats = stats
                                emitter.onNext(stats)
                                emitter.onComplete()
                            } catch (e: IOException) {
                                emitter.onError(e)
                            }
                        })
            } else {
                emitter.onNext(storageStats!!)
                emitter.onComplete()
            }
        }, BackpressureStrategy.BUFFER)
    }
}