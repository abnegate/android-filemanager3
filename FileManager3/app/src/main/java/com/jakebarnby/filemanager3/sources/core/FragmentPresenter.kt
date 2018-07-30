package com.jakebarnby.filemanager3.sources.core

import android.content.Context
import com.jakebarnby.filemanager3.di.ActivityScoped
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScoped
open class FragmentPresenter @Inject constructor(): SourceContract.FragmentPresenter {

    lateinit var source: Source

    private var view: SourceContract.FragmentView? = null

    override fun subscribe(view: SourceContract.FragmentView) {
        this.view = view
    }

    override fun unsubscribe() {
        view = null
    }

    override fun onConnectClicked() {
        val loginAndLoad = Flowable.concat(
                source.authenticateSource(view as Context).toFlowable(),
                source.loadSource(view as Context).toFlowable())

        source.disposables.add(loginAndLoad
                .subscribeOn(Schedulers.io())
                .subscribe())
    }

    override fun onFileClicked(file: SourceFile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBreadcrumbClicked(file: SourceFile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}