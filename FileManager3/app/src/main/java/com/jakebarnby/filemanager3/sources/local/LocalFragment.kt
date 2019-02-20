package com.jakebarnby.filemanager3.sources.local

import com.jakebarnby.filemanager3.data.FileDatabase
import com.jakebarnby.filemanager3.sources.core.SourceFileCollectionPresenter
import com.jakebarnby.filemanager3.sources.core.SourceFragment
import com.jakebarnby.filemanager3.ui.breadcrumbs.BreadcrumbPresenter

class LocalFragment : SourceFragment() {
    init {
        fragmentPresenter = LocalPresenter().also {
            breadcrumbPresenter = it.breadcrumbPresenter
        }
        fileCollectionPresenter = SourceFileCollectionPresenter(fragmentPresenter)

    }
}