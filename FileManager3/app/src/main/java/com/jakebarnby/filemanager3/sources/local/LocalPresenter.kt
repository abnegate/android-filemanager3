package com.jakebarnby.filemanager3.sources.local

import com.jakebarnby.filemanager3.sources.core.SourceFragmentPresenter

class LocalPresenter : SourceFragmentPresenter() {

    init {
        source = LocalSource()
    }
}