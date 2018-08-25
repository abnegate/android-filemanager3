package com.jakebarnby.filemanager3.data.helpers

import io.reactivex.Single

interface IdHelper<T> {
    fun getId(obj: T): Single<Long>
}