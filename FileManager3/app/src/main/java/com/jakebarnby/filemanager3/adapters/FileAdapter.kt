package com.jakebarnby.filemanager3.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.jakebarnby.filemanager3.R
import com.jakebarnby.filemanager3.R.id.img_file_preview
import com.jakebarnby.filemanager3.sources.models.SourceFile
import kotlinx.android.synthetic.main.view_file_list.view.*

abstract class FileAdapter() : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    private val fileList = mutableListOf<SourceFile>()

    private lateinit var onClick: () -> Unit
    private lateinit var onLongClick: () -> Unit

    override fun getItemCount(): Int {
        return fileList.size
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bindHolder(fileList[position])
    }

    class FileViewHolder : RecyclerView.ViewHolder {
        fun bindHolder(file: SourceFile) {

        }
    }
}