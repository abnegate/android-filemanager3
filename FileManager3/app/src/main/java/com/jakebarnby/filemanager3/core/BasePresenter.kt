package com.jakebarnby.filemanager3.core

interface BasePresenter<T> {
    fun subscribe(view: T)
    fun unsubscribe()
}
