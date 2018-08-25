package com.jakebarnby.filemanager3.sources.models

import android.content.Context
import com.jakebarnby.filemanager3.data.FileDao
import com.jakebarnby.filemanager3.data.FileDatabase
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Jake on 7/29/2017.
 */

abstract class Source(val sourceType: SourceType, val sourceName: String) {

    lateinit var fileDao: FileDao

    var rootFileId: Long = 0L
    var storageStats: StorageStats? = null
    var isLoggedIn = false
    var isFilesLoaded = false
    var isMultiSelectEnabled = false
    val disposables = CompositeDisposable()

    var currentFolderParentId = 0L

    abstract fun authenticateSource(context: Context): Flowable<Any>
    abstract fun loadSource(context: Context): Flowable<Any>
    abstract fun logout(context: Context): Flowable<Any>
    abstract fun getStorageStats(): Flowable<StorageStats>

    fun dispose() {
        disposables.dispose()
    }

    fun setFileSource(context: Context) {
        fileDao = FileDatabase.getInstance(context).fileDao()
    }
}

