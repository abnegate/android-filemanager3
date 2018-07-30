package com.jakebarnby.filemanager3.data.helpers

import io.reactivex.Single

interface FileIdHelper<T> {
    fun getId(file: T): Single<Long>
}