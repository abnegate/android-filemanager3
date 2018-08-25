package com.jakebarnby.filemanager3.util

import android.view.View

fun View.toggleVisibility() {
    var visibilityToSet = 0
    when (visibility) {
        View.VISIBLE -> visibilityToSet = View.GONE
        View.GONE -> visibilityToSet = View.VISIBLE
    }
    visibility = visibilityToSet
}