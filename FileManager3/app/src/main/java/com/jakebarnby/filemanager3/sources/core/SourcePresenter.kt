package com.jakebarnby.filemanager3.sources.core

import com.jakebarnby.filemanager3.di.ActivityScoped
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

    override fun onPaseClicked() {
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
