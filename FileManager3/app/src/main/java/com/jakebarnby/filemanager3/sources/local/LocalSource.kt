package com.jakebarnby.filemanager3.sources.local

import android.content.Context
import android.os.Environment
import android.os.StatFs
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile
import com.jakebarnby.filemanager3.sources.models.SourceType
import com.jakebarnby.filemanager3.sources.models.StorageStats
import com.jakebarnby.filemanager3.util.Constants.Sources.LOCAL
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
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
        return Flowable.create({ emitter ->
            val root = Environment.getExternalStorageDirectory()
            val queue = LinkedList<File>()
            val fileList = mutableListOf<SourceFile>()

            var parentId = 0L

            queue.offer(root)

            while (!queue.isEmpty()) {
                val file = queue.poll()

                file.parentFile?.let {
                    parentId = it.hashCode().toLong()
                }

                val sourceFile = SourceFile(file, parentId)

                if (fileList.size == 0) {
                    rootFileId = sourceFile.id
                }

                fileList.add(sourceFile)
                emitter.onNext(sourceFile)

                if (file.isDirectory) {
                    file.listFiles().forEach {
                        queue.offer(it)
                    }
                }
            }
            fileDao!!.insertFiles(fileList)
            emitter.onComplete()
        }, BackpressureStrategy.BUFFER)
    }

    override fun logout(context: Context): Flowable<Any> =
        Flowable.create({
            it.onComplete()
        }, BackpressureStrategy.BUFFER)

    override fun getStorageStats(): Single<StorageStats> {
        return Single.create { emitter ->
            if (storageStats != null) {
                emitter.onSuccess(storageStats!!)
                return@create
            }

            val stats = StorageStats()
            disposables.add(
                fileDao!!
                    .getFileById(rootFileId)
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
                            emitter.onSuccess(stats)
                        } catch (e: IOException) {
                            emitter.onError(e)
                        }
                    }
            )
        }
    }
}