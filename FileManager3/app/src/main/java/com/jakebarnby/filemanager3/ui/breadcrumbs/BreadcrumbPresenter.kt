package com.jakebarnby.filemanager3.ui.breadcrumbs

import android.content.Context
import android.view.View
import com.jakebarnby.filemanager3.sources.core.SourceContract
import com.jakebarnby.filemanager3.sources.core.SourceFragmentPresenter
import com.jakebarnby.filemanager3.sources.models.SourceFile
import java.util.Stack

class BreadcrumbPresenter(private val fragmentPresenter: SourceContract.FragmentPresenter) :
    BreadcrumbContract.Presenter {

    private val crumbs: Stack<SourceFile> = Stack()

    override fun bindViewForPosition(position: Int, view: BreadcrumbContract.View) {
        crumbs[position]?.let {
            when (it.id) {
                (fragmentPresenter as? SourceFragmentPresenter)?.source?.rootFileId -> {
                    view.setText(it.sourceName)
                    view.setArrowVisibility(View.GONE)
                }
                else -> view.setText(it.name)
            }
        }
    }

    override fun pushBreadcrumb(file: SourceFile) {
        crumbs.push(file)
        fragmentPresenter.breadcrumbAdded(crumbs.size - 1)
    }

    override fun popBreadcrumb() {
        val position = crumbs.size
        crumbs.pop()
        fragmentPresenter.breadcrumbRemoved(position)
    }

    override fun onItemSelected(position: Int, context: Context?) {
        context?.let {
            val diff = crumbs.size - position - 1

            for (i in 0..diff) {
                crumbs.pop()
            }

            // TODO("this why breadcrumbs are broken")
            fragmentPresenter.onFileSelected(crumbs[position], context)
        }
    }

    override fun getItemCount(): Int {
        return crumbs.size
    }
}