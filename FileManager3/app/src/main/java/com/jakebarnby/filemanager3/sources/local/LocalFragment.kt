package com.jakebarnby.filemanager3.sources.local

import com.jakebarnby.filemanager3.sources.core.SourceFragment

class LocalFragment : SourceFragment() {
    init {
        fragmentPresenter = LocalPresenter()
    }
}