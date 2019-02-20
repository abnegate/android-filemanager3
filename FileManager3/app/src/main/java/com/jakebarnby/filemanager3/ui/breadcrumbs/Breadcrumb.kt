package com.jakebarnby.filemanager3.ui.breadcrumbs

data class Breadcrumb(
    val text: String,
    val fileId: Long,
    val parentFileId: Long
)