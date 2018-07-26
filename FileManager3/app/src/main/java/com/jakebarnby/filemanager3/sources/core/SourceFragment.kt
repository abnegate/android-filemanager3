package com.jakebarnby.filemanager3.sources.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakebarnby.filemanager3.R

open class SourceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val newView = inflater.inflate(R.layout.fragment_source, container, false)

        return newView
    }
}