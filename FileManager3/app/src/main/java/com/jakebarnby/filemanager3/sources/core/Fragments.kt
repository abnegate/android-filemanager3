package com.jakebarnby.filemanager3.sources.core

import com.jakebarnby.filemanager3.sources.local.LocalFragment
import com.jakebarnby.filemanager3.util.Constants

enum class Fragments(val title: String, val fragment: SourceFragment) {
    Local(Constants.Sources.LOCAL, LocalFragment())
}