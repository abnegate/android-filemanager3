package com.jakebarnby.filemanager3.ui.breadcrumbs

import android.content.Context
import com.jakebarnby.filemanager3.sources.models.SourceFile

interface BreadcrumbContract {

    interface View {
        fun setText(folderName: String)
        fun setArrowVisibility(visibility: Int)
    }

    interface Presenter {
        fun pushBreadcrumb(file: SourceFile)
        fun popBreadcrumb()
        fun bindViewForPosition(position: Int, view: View)
        fun onItemSelected(position: Int, context: Context?)
        fun getItemCount(): Int
    }
}