package com.jakebarnby.filemanager3.sources.core

import android.support.v4.app.Fragment
import com.jakebarnby.filemanager3.di.ActivityScoped
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@ActivityScoped
open class SourceFragmentPresenter : SourceContract.FragmentPresenter {

    protected lateinit var source: Source
    private lateinit var fileConsumer: (fileList: List<SourceFile>) -> Unit
    private lateinit var errorConsumer: (error: Throwable) -> Unit
    private lateinit var completeAction: () -> Unit

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
                    .observeOn(AndroidSchedulers.mainThread())
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

    override fun loadRootFolder(onNext: (files: List<SourceFile>) -> Unit,
                                onError: (error: Throwable) -> Unit,
                                onComplete: () -> Unit) {
        fileConsumer = onNext
        errorConsumer = onError
        completeAction = onComplete

        source.disposables.add(source.fileDao
            .getFileByIdAndSource(source.rootFileId, source.sourceName)
            //.getFiles()
            .subscribeOn(Schedulers.io())
            .subscribe({
                openFile(it)
            }, {
                onError(it)
            }, {

            })
        )
    }

    override fun openFile(file: SourceFile) {
        if (file.isDirectory) {
            source.disposables.add(source.fileDao
                .getFolderBySource(file.id, source.sourceName)
                .parallel()
                .runOn(Schedulers.io())
                .sequential()
                .subscribe({
                    fileConsumer(it)
                }, {
                    errorConsumer(it)
                }, {
                    completeAction
                }))
        } else {

        }
    }

    override fun navigateToBreadcrumb(file: SourceFile) {
        TODO("not implemented")
    }
}