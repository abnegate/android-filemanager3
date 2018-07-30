package com.jakebarnby.filemanager3.sources.local

import com.jakebarnby.filemanager3.sources.core.FragmentPresenter

class LocalPresenter: FragmentPresenter() {

    init {
        source = LocalSource()
    }
}