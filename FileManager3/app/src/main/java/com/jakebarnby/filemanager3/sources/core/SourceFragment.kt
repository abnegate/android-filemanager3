package com.jakebarnby.filemanager3.sources.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakebarnby.filemanager3.R
import com.jakebarnby.filemanager3.adapters.FileAdapter
import com.jakebarnby.filemanager3.adapters.FileListAdapter
import com.jakebarnby.filemanager3.data.FileDatabase
import com.jakebarnby.filemanager3.ui.breadcrumbs.BreadcrumbAdapter
import com.jakebarnby.filemanager3.ui.breadcrumbs.BreadcrumbContract
import com.jakebarnby.filemanager3.util.toggleVisibility
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.fragment_source.view.*

open class SourceFragment : Fragment(), SourceContract.FragmentView {

    lateinit var fragmentPresenter: SourceContract.FragmentPresenter
    lateinit var fileCollectionPresenter: SourceContract.FileCollectionPresenter
    lateinit var breadcrumbPresenter: BreadcrumbContract.Presenter

    private lateinit var filesRecycler: RecyclerView
    private lateinit var breadcrumbRecycler: RecyclerView
    private lateinit var adapter: FileAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_source, container, false)
        view.btn_connect.setOnClickListener {
            fragmentPresenter.connect {
                initViews()
                showRootFolder()
            }
        }
        filesRecycler = view.findViewById(R.id.recycler_files)
        breadcrumbRecycler = view.findViewById(R.id.recycler_breadcrumbs)
        fragmentPresenter.setFileDao(FileDatabase.getInstance(context!!).fileDao())
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

    override fun initViews() {
        adapter = FileListAdapter(fileCollectionPresenter)
        filesRecycler.adapter = adapter
        filesRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        breadcrumbRecycler.adapter = BreadcrumbAdapter(breadcrumbPresenter)
        breadcrumbRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun showRootFolder() {
        fragmentPresenter.loadRootFolder({ files ->
            fileCollectionPresenter.setCurrentDirectory(files)
            activity?.runOnUiThread {
                when (img_source_logo.visibility) {
                    View.VISIBLE -> toggleLogo()
                }
            }
        }, {
            it.printStackTrace()
        }, {
            adapter.notifyDataSetChanged()
        })
    }

    override fun breadcrumbAdded(position: Int) {
        breadcrumbRecycler.adapter?.notifyItemInserted(position)
        activity?.let {
            it.runOnUiThread {
            }
        }
    }

    override fun breadcrumbRemoved(position: Int) {
        breadcrumbRecycler.adapter?.notifyItemRangeRemoved(position, 1)
        activity?.let {
            it.runOnUiThread {

            }
        }
    }

    override fun toggleLoading() {
        pgb_loading.toggleVisibility()
    }

    override fun toggleConnectButton() {
        btn_connect.toggleVisibility()
    }

    override fun toggleLogo() {
        img_source_logo.toggleVisibility()
    }

    override fun showErrorSnackbar(error: String) {
        Log.e("ERROR", error)
    }

    override fun showMessageSnackbar(message: String) {
        TODO("not implemented")
    }

    override fun showErrorWithActionSnackbar(error: String, listener: () -> Unit) {
        TODO("not implemented")
    }


}