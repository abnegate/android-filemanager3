package com.jakebarnby.filemanager3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakebarnby.filemanager3.R
import com.jakebarnby.filemanager3.sources.core.SourceContract

class FileListAdapter(filePresenter: SourceContract.FilePresenter)
    : FileAdapter(filePresenter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val inflatedView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_file_list, parent, false)
        return FileViewHolder(inflatedView, filePresenter)
    }
}