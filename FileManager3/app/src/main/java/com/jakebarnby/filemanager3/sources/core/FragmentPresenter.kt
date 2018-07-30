package com.jakebarnby.filemanager3.sources.core

import android.support.v4.app.Fragment
import com.jakebarnby.filemanager3.di.ActivityScoped
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

@ActivityScoped
open class FragmentPresenter : SourceContract.FragmentPresenter {

    protected lateinit var source: Source

    private var view: SourceContract.FragmentView? = null

    override fun subscribe(view: SourceContract.FragmentView) {
        this.view = view
    }

    override fun unsubscribe() {
        view = null
    }

    override fun connect(onComplete: () -> Unit) {
        val context = (view as Fragment).context
        context?.let {
            val loginAndLoad = Flowable.concat(
                    source.authenticateSource(it).toFlowable(),
                    source.loadSource(it).toFlowable())
                    .doOnComplete(onComplete)

            source.disposables.add(loginAndLoad
                    .subscribeOn(Schedulers.io())
                    .subscribe())
        }
    }

    override fun openFile(file: SourceFile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToBreadcrumb(file: SourceFile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}