package com.jakebarnby.filemanager3.sources.core

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.util.Log
import com.jakebarnby.filemanager3.R
import com.jakebarnby.filemanager3.data.FileDao
import com.jakebarnby.filemanager3.di.ActivityScoped
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile
import com.jakebarnby.filemanager3.ui.breadcrumbs.BreadcrumbContract
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

@ActivityScoped
open class SourceFragmentPresenter : SourceContract.FragmentPresenter {

    lateinit var source: Source
    lateinit var breadcrumbPresenter: BreadcrumbContract.Presenter
    private lateinit var fileConsumer: (fileList: List<SourceFile>) -> Unit
    private lateinit var errorConsumer: (error: Throwable) -> Unit
    private lateinit var completeAction: () -> Unit

    private var view: SourceContract.FragmentView? = null

    override fun subscribe(view: SourceContract.FragmentView) {
        this.view = view
    }

    override fun unsubscribe() {
        view = null
    }

    override fun setFileDao(fileDao: FileDao) {
        if (source.fileDao == null) {
            source.fileDao = fileDao
        }
    }

    override fun connect(onComplete: () -> Unit) {
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

                val getStats = source.getStorageStats().toFlowable().doOnNext { stats ->
                    Log.e("STATS", "Free space ${stats.freeSpace}, total space: ${stats.totalSpace}")
                }

                source.disposables.add(
                    Flowable.concat(login, Flowable.merge(load, getStats))
                        .subscribeOn(Schedulers.io())
                        .subscribe())
            }
        }
    }

    override fun loadRootFolder(
        onNext: (files: List<SourceFile>) -> Unit,
        onError: (error: Throwable) -> Unit,
        onComplete: () -> Unit
    ) {
        fileConsumer = onNext
        errorConsumer = onError
        completeAction = onComplete

        if (source.fileDao == null) {
            return
        }

        source.disposables.add(source.fileDao!!
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

    override fun onFileSelected(
        file: SourceFile,
        context: Context?,
        pushBreadcrumb: Boolean
    ) {
        if (!file.isDirectory) {
            openFile(file, context)
            return
        }

        if (source.fileDao == null) {
            return
        }

        source.disposables.add(source.fileDao!!
            .getFolderBySource(file.id, source.sourceName)
            .subscribeOn(Schedulers.io())
            .subscribe({
                fileConsumer(it)
            }, {
                it.printStackTrace()
                errorConsumer(it)
            }, {
                completeAction
            }))
    }

    override fun breadcrumbAdded(position: Int) {
        view?.breadcrumbAdded(position)
    }

    override fun breadcrumbRemoved(position: Int) {
        view?.breadcrumbRemoved(position)
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
                    view?.showErrorSnackbar(context.getString(R.string.err_no_app_available))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            view?.let { view ->
                context?.let { ctx ->
                    view.showErrorSnackbar(String.format(
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