package com.jakebarnby.filemanager3.sources.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.HorizontalScrollView
import com.jakebarnby.filemanager3.R
import com.jakebarnby.filemanager3.sources.models.SourceFile
import com.jakebarnby.filemanager3.util.toggleVisibility
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.fragment_source.view.*
import kotlinx.android.synthetic.main.view_breadcrumb.view.*

open class SourceFragment : Fragment(), SourceContract.FragmentView {

    lateinit var fragmentPresenter: SourceFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_source, container, false)
        view.btn_connect.setOnClickListener {
            fragmentPresenter.connect {
                //TODO: Load data into view
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        fragmentPresenter.subscribe(this)
    }

    override fun onStop() {
        fragmentPresenter.unsubscribe()
        super.onStop()
    }

    override fun toggleLoading() {
        pgb_loading.toggleVisibility()
    }

    override fun toggleConnectButton() {
        btn_connect.toggleVisibility()
    }

    override fun createBreadcrumb(file: SourceFile): View {
        val crumbLayout = layoutInflater
            .inflate(R.layout.view_breadcrumb, null) as ViewGroup

        crumbLayout.crumb_arrow.visibility =
            if (file.parentId == 0)
                View.GONE
            else
                View.VISIBLE

        crumbLayout.crumb_text.text =
            if (file.parentId == 0)
                file.sourceName
            else
                file.name

        crumbLayout.setOnClickListener(breadcrumbClickListener(crumbLayout))
        return crumbLayout
    }

    override fun pushBreadcrumb(crumb: View) {
        breadcrumb_scroll.postDelayed({ breadcrumb_scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT) },
            50L)
        breadcrumbs.addView(view)
        crumb.startAnimation(AnimationUtils.loadAnimation(context, R.anim.breadcrumb_overshoot_z))
    }

    override fun popBreadcrumb() {
        breadcrumbs.removeViewAt(breadcrumbs.childCount - 1)
    }

    private fun breadcrumbClickListener(crumb: View): View.OnClickListener {
        return View.OnClickListener {
            //            val crumbText = it.crumb_text
//
//            val diff = breadcrumbs.childCount - 1 - breadcrumbs.indexOfChild(crumb)
//            for (i in 0 until diff) popBreadcrumb()
//
//            val name = crumbText.text.toString()
//            if (mSource.getCurrentDirectory().getData().getName().equals(name)) return@crumbLayout.setOnClickListener
//            val selectedParent = TreeNode.searchForParent(mSource.getCurrentDirectory(), name)
//
//            val previousPosition = selectedParent.getData().getPositionToRestore()
//            if (previousPosition != -1) {
//                (rv.getLayoutManager() as LinearLayoutManager).scrollToPositionWithOffset(previousPosition, 0)
//            }
//
//            (activity as SourceActivity).getSourceManager().setActiveDirectory(selectedParent)
//            (recycler_files.getAdapter() as FileAdapter).setCurrentDirectory(selectedParent, context)
//            mSource.setCurrentDirectory(selectedParent)
//            recycler_files.getAdapter().notifyDataSetChanged()
        }
    }
}