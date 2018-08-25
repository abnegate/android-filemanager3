package com.jakebarnby.filemanager3.sources.core

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import com.jakebarnby.filemanager3.R
import com.jakebarnby.filemanager3.di.ActivityScoped
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

@ActivityScoped
open class SourceFragmentPresenter : SourceContract.FragmentPresenter {

    lateinit var source: Source
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

    override fun getSourceObj(): Source {
        return source
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
            .subscribeOn(Schedulers.io())
            .subscribe({
                onFileSelected(it, (view as Fragment).context)
            }, {
                it.printStackTrace()
                onError(it)
            }, {

            })
        )
    }

    override fun onFileSelected(file: SourceFile, context: Context?) {
        if (file.isDirectory) {

            source.disposables.add(source.fileDao
                .getFolderBySource(file.id, source.sourceName)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    source.currentFolderParentId = file.parentId
                    fileConsumer(it)
                }, {
                    it.printStackTrace()
                    errorConsumer(it)
                }, {
                    completeAction
                }))
        } else {
            openFile(file, context)
        }
    }

    override fun openFile(file: SourceFile, context: Context?) {
        try {
            context?.let { ctx ->
                val localFile = File(file.path)
                val reachableUri = FileProvider.getUriForFile(ctx, "com.jakebarnby.filemanager3.provider", localFile)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(reachableUri, file.mimeType)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

                val manager = ctx.packageManager
                val resolveInfo = manager.queryIntentActivities(intent, 0)
                if (resolveInfo.size > 0) {
                    ctx.startActivity(intent)
                } else {
                    view?.let {
                        it.showErrorSnackbar(context.getString(R.string.err_no_app_available))
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            view?.let { views ->
                context?.let { ctx ->
                    views.showErrorSnackbar(String.format(
                        "%s %s %s",
                        ctx.getString(R.string.problem_encountered),
                        ctx.getString(R.string.opening_file),
                        ": " + e.localizedMessage))
                }
            }
        }

    }

    override fun navigateToBreadcrumb(file: SourceFile) {
        TODO("not implemented")
    }
}