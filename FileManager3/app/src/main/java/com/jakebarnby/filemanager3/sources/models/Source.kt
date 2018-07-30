package com.jakebarnby.filemanager3.sources.models

import android.content.Context
import com.jakebarnby.filemanager3.data.FileDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Jake on 7/29/2017.
 */

abstract class Source(val sourceType: SourceType, val sourceName: String) {

    @Inject
    lateinit var fileDao: FileDao

    var rootFileId = 0
    var storageStats: StorageStats? = null
    var isLoggedIn = false
    var isFilesLoaded = false
    var isMultiSelectEnabled = false
    val disposables = CompositeDisposable()

    abstract fun authenticateSource(context: Context): Single<Any>
    abstract fun loadSource(context: Context): Completable
    abstract fun logout(context: Context): Completable
    abstract fun getStorageStats(): Single<StorageStats>

    fun dispose() {
        disposables.dispose()
    }
}

