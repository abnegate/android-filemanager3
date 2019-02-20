package com.jakebarnby.filemanager3.sources.local

import com.jakebarnby.filemanager3.sources.core.SourceFragmentPresenter
import com.jakebarnby.filemanager3.ui.breadcrumbs.BreadcrumbPresenter

class LocalPresenter : SourceFragmentPresenter() {

    init {
        source = LocalSource()
        breadcrumbPresenter = BreadcrumbPresenter(this)
    }
}