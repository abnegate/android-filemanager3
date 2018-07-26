package com.jakebarnby.filemanager3.sources.models

import android.content.Context
import com.jakebarnby.filemanager3.data.FileDao
import javax.inject.Inject

/**
 * Created by Jake on 7/29/2017.
 */

abstract class Source(val sourceType: SourceType, val sourceName: String) {

    @Inject
    lateinit var fileDao: FileDao

    var storageStats: StorageStats? = null
    var isLoggedIn: Boolean = false
    var isFilesLoaded: Boolean = false
    var isMultiSelectEnabled: Boolean = false

    abstract fun authenticateSource(context: Context)
    abstract fun loadSource(context: Context)
    abstract fun logout(context: Context)
}

