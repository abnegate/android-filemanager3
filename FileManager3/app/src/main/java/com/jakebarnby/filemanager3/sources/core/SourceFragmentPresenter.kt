package com.jakebarnby.filemanager3.sources.core

import android.support.v4.app.Fragment
import com.jakebarnby.filemanager3.di.ActivityScoped
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

@ActivityScoped
open class SourceFragmentPresenter : SourceContract.FragmentPresenter {

    protected lateinit var source: Source

    private var view: SourceContract.FragmentView? = null

    override fun subscribe(view: SourceContract.FragmentView) {
        this.view = view
        source.setFileSource((view as Fragment).context!!)
    }

    override fun unsubscribe() {
        view = null
    }

    override fun connect(onComplete: ConnectListener) {
        view?.let { view ->
            view.toggleConnectButton()
            view.toggleLoading()

            val context = (view as Fragment).context
            context?.let {
                val login = source.authenticateSource(it)
                val load = source.loadSource(it)
                    .doOnComplete {
                        view.toggleLoading()
                        onComplete()
                    }

                val getStats = source.getStorageStats()

                source.disposables.add(
                    Flowable.concat(login, Flowable.merge(load, getStats))
                        .subscribeOn(Schedulers.io())
                        .subscribe())
            }
        }
    }

    override fun openFile(file: SourceFile) {
        TODO("not implemented")
    }

    override fun navigateToBreadcrumb(file: SourceFile) {
        TODO("not implemented")
    }
}