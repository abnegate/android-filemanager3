package com.jakebarnby.filemanager3.sources.local

import android.content.Context
import android.os.Environment
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile
import com.jakebarnby.filemanager3.sources.models.SourceType
import com.jakebarnby.filemanager3.util.Constants.Sources.LOCAL
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.util.*

class LocalSource : Source(SourceType.LOCAL, LOCAL) {

    override fun authenticateSource(context: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadSource(context: Context) {
        Completable.create {
            val root = Environment.getExternalStorageDirectory()
            val queue = LinkedList<File>()
            queue.push(root)

            var parentId = 0
            while (!queue.isEmpty()) {
                val file = queue.poll()
                val sourceFile = SourceFile(file, parentId)

                fileDao.insertFile(sourceFile)

                if (file.isDirectory) {
                    parentId = file.hashCode()
                    file.listFiles().forEach { queue.add(it) }
                }
            }
        }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun logout(context: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}