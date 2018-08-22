package com.jakebarnby.filemanager3.sources.core

import android.app.Activity
import com.jakebarnby.filemanager3.di.ActivityScoped
import com.sembozdemir.permissionskt.askPermissions
import javax.inject.Inject

@ActivityScoped
class SourcePresenter @Inject constructor() : SourceContract.Presenter {

    private var view: SourceContract.View? = null

    override fun subscribe(view: SourceContract.View) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
    }

    override fun checkPermissions() {
        (view as Activity)
            .askPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                onGranted {

                }

                onDenied {

                }

                onShowRationale {
                    view?.showErrorDialog()
                }

                onNeverAskAgain {
                    view?.showErrorWithActionSnackbar("Never ask again.") {
                        //TODO: Open settings
                    }
                }
            }
    }

    override fun onViewAsClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateFolderClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateZipClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPropertiesClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUsageClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSortByClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSettingsClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCopyClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPasteClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDeleteClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchClcked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onOpenClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addLocalSource(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addLocalSources() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
