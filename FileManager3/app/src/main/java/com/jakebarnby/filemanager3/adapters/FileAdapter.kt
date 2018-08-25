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

abstract class FileAdapter(protected val filePresenter: SourceContract.FilePresenter)
    : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    override fun getItemCount(): Int {
        return filePresenter.getFileCount()
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        filePresenter.bindViewForPosition(position, holder)
    }

    open class FileViewHolder(view: View?,
                              protected val filePresenter: SourceContract.FilePresenter) :
        RecyclerView.ViewHolder(view),
        SourceContract.FileRowView,
        View.OnClickListener {

        private lateinit var imgPreview: ImageView
        private lateinit var selectedBox: CheckBox
        private lateinit var filename: TextView

        init {
            view?.let {
                it.setOnClickListener(this)
                imgPreview = it.findViewById(R.id.img_file_preview)
                selectedBox = it.findViewById(R.id.checkbox)
                filename = it.findViewById(R.id.txt_item_title)
            }
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
            filePresenter.onItemSelected(adapterPosition, view?.context)
        }
    }
}