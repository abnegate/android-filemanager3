package com.jakebarnby.filemanager3.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jakebarnby.filemanager3.R
import com.jakebarnby.filemanager3.glide.GlideApp
import com.jakebarnby.filemanager3.sources.core.SourceContract

abstract class FileAdapter(protected val fileCollectionPresenter: SourceContract.FileCollectionPresenter)
    : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    override fun getItemCount(): Int {
        return fileCollectionPresenter.getFileCount()
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        fileCollectionPresenter.bindViewForPosition(position, holder)
    }

    class FileViewHolder(
        view: View,
        private val fileCollectionPresenter: SourceContract.FileCollectionPresenter
    ) : RecyclerView.ViewHolder(view),
        SourceContract.FileCollectionView,
        View.OnClickListener {

        private var imgPreview: ImageView
        private var selectedBox: CheckBox
        private var filename: TextView

        init {
            view.setOnClickListener(this)
            imgPreview = view.findViewById(R.id.img_file_preview)
            selectedBox = view.findViewById(R.id.checkbox)
            filename = view.findViewById(R.id.txt_item_title)
        }

        override fun setPreviewImage(path: String) {
            GlideApp
                .with(itemView)
                .load(path)
                .error(R.drawable.ic_file)
                .placeholder(R.drawable.ic_file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.2f)
                .circleCrop()
                .into(imgPreview)
        }

        override fun setFilename(name: String) {
            filename.text = name
        }

        override fun setSelected(selected: Boolean) {
            selectedBox.isSelected = selected
        }

        override fun onClick(view: View?) {
            fileCollectionPresenter.onItemSelected(adapterPosition, view?.context)
        }
    }
}