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
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.IOException
import java.util.*

class LocalSource : Source(SourceType.LOCAL, LOCAL) {

    override fun authenticateSource(context: Context): Single<Any> {
        return Single.create {
            it.onSuccess(true)
        }
    }

    override fun loadSource(context: Context): Completable {
        return Completable.create {
            val root = Environment.getExternalStorageDirectory()
            val queue = LinkedList<File>()
            queue.offer(root)

            var parentId = 0
            var iterCount = 0
            var setNewIdIn = Int.MAX_VALUE
            rootFileId = parentId
            while (!queue.isEmpty()) {
                val file = queue.poll()

                if (iterCount == setNewIdIn) {
                    parentId = file.hashCode()
                    setNewIdIn = Int.MAX_VALUE
                }

                val sourceFile = SourceFile(file, parentId)

                fileDao.insertFile(sourceFile)

                Log.d("File inserted", file.absolutePath)

                if (file.isDirectory) {
                    val initialLength = queue.size
                    file.listFiles().forEach { queue.offer(it) }
                    val newLength = queue.size
                    setNewIdIn = newLength - initialLength
                    iterCount = -1
                }
                iterCount++
            }
        }
    }

    override fun logout(context: Context): Completable {
        return Completable.create {
            it.onComplete()
        }
    }

    override fun getStorageStats(): Single<StorageStats> {
        return Single.create { emitter ->
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
                                        emitter.onSuccess(stats)
                                    } catch (e: IOException) {
                                        emitter.onError(e)
                                    }
                                })
            } else {
                emitter.onSuccess(storageStats!!)
            }
        }
    }
}