package com.jakebarnby.filemanager3.core

import android.view.View

interface BaseView<T> {
    fun showErrorSnackbar(error: String)
    fun showMessageSnackbar(message: String)
    fun showErrorWithActionSnackbar(error: String, listener: View.OnClickListener)
}
