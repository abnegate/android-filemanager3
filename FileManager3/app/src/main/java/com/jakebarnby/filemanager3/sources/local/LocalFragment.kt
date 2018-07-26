package com.jakebarnby.filemanager3.sources.local

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakebarnby.filemanager3.sources.core.SourceContract
import com.jakebarnby.filemanager3.sources.core.SourceFragment
import com.jakebarnby.filemanager3.sources.models.SourceFile
import javax.inject.Inject

class LocalFragment : SourceFragment(), SourceContract.FragmentView {

    @Inject private lateinit var presenter: SourceContract.FragmentPresenter

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toggleConnectButton(enabled: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushBreadcrumb(file: SourceFile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popBreadcrumb() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}