package com.jakebarnby.filemanager3.ui.breadcrumbs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakebarnby.filemanager3.R

class BreadcrumbAdapter(private val presenter: BreadcrumbContract.Presenter) :
    RecyclerView.Adapter<BreadcrumbAdapter.BreadcrumbViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreadcrumbViewHolder {
        val inflatedView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_breadcrumb, parent, false)
        return BreadcrumbViewHolder(inflatedView, presenter)
    }

    override fun getItemCount(): Int {
        return presenter.getItemCount()
    }

    override fun onBindViewHolder(holder: BreadcrumbViewHolder, position: Int) {
        presenter.bindViewForPosition(position, holder)
    }

    class BreadcrumbViewHolder(
        view: View,
        private val presenter: BreadcrumbContract.Presenter
    ) : RecyclerView.ViewHolder(view),
        BreadcrumbContract.View,
        View.OnClickListener {

        private var text: TextView = view.findViewById(R.id.crumb_text)
        private var arrow: TextView = view.findViewById(R.id.crumb_arrow)

        init {
            view.setOnClickListener(this)
        }

        override fun setText(folderName: String) {
            text.text = folderName
        }

        override fun setArrowVisibility(visibility: Int) {
            arrow.visibility = visibility
        }

        override fun onClick(view: View?) {
            view?.let {
                presenter.onItemSelected(adapterPosition, view.context)
            }
        }
    }
}