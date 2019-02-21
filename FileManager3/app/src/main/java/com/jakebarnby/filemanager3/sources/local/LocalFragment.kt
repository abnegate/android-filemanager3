package com.jakebarnby.filemanager3.sources.local

import com.jakebarnby.filemanager3.sources.core.SourceFileCollectionPresenter
import com.jakebarnby.filemanager3.sources.core.SourceFragment

class LocalFragment : SourceFragment() {
    init {
        fragmentPresenter = LocalPresenter().also {
            breadcrumbPresenter = it.breadcrumbPresenter
        }
        fileCollectionPresenter = SourceFileCollectionPresenter(fragmentPresenter)
    }
}