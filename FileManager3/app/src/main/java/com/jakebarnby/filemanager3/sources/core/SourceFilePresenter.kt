package com.jakebarnby.filemanager3.sources.core

import com.jakebarnby.filemanager3.sources.models.SourceFile

class SourceFilePresenter(private val fragmentPresenter: SourceContract.FragmentPresenter)
    : SourceContract.FilePresenter {

    private val files: MutableList<SourceFile> = mutableListOf()

    override fun onItemSelected(position: Int) {
        fragmentPresenter.openFile(files[position])
    }

    override fun getFileCount(): Int {
        return files.size
    }

    override fun bindViewForPoistion(position: Int, fileRowView: SourceContract.FileRowView) {
        val file = files[position]
        file.let {
            fileRowView.setFilename(it.name!!)
            if (!file.isDirectory && file.isMediaFile) {
                fileRowView.setPreviewImage(it.thumbnailLink!!)
            }
        }
    }

    override fun setCurrentDirectory(fileList: List<SourceFile>) {
        files.clear()
        files.addAll(fileList)
    }
}