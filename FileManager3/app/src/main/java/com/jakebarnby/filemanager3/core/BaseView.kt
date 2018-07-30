package com.jakebarnby.filemanager3.core

interface BaseView<T> {
    fun showErrorSnackbar(error: String)
    fun showMessageSnackbar(message: String)
    fun showErrorWithActionSnackbar(error: String, listener: () -> Unit)
}
