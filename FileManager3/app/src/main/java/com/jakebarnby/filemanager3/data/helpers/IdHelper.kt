package com.jakebarnby.filemanager3.data.helpers

interface IdHelper<T> {
    fun getId(obj: T): Long
}