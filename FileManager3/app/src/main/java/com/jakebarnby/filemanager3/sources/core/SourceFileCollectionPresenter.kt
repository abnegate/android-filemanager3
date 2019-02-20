package com.jakebarnby.filemanager3.sources.core

import android.content.Context
import com.jakebarnby.filemanager3.sources.models.SourceFile

class SourceFileCollectionPresenter(private val fragmentPresenter: SourceContract.FragmentPresenter)
    : SourceContract.FileCollectionPresenter {

    private val files: MutableList<SourceFile> = mutableListOf()

    override fun onItemSelected(position: Int, context: Context?) {
        fragmentPresenter.onFileSelected(files[position], context)
    }

    override fun getFileCount(): Int {
        return files.size
    }

    override fun bindViewForPosition(position: Int, fileCollectionView: SourceContract.FileCollectionView) {
        val file = files[position]
        file.let {
            fileCollectionView.setFilename(it.name)
            if (!file.isDirectory && file.isMediaFile) {
                fileCollectionView.setPreviewImage(it.thumbnailLink)
            }
        }
    }

    override fun setCurrentDirectory(fileList: List<SourceFile>) {
        files.clear()
        files.addAll(fileList)
    }
}