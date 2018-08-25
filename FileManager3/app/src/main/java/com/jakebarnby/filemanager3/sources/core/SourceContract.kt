package com.jakebarnby.filemanager3.sources.core

import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import com.jakebarnby.filemanager3.core.BasePresenter
import com.jakebarnby.filemanager3.core.BaseView
import com.jakebarnby.filemanager3.sources.models.Source
import com.jakebarnby.filemanager3.sources.models.SourceFile

class SourceContract {

    interface View : BaseView<Presenter>, ViewPager.OnPageChangeListener, SearchView.OnQueryTextListener {
        fun showViewAsDialog()
        fun showCreateFolderDialog()
        fun showCreateZipDialog()
        fun showPropertiesDialog()
        fun showUsageDialog()
        fun showLogoutDialog()
        fun showSortByDialog()
        fun showSettingsDialog()
        fun showErrorDialog()
        fun showAd()

        fun setupFloatingMenu()
        fun toggleFloatingMenu(enabled: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun checkPermissions()

        fun onViewAsClicked()
        fun onCreateFolderClicked()
        fun onCreateZipClicked()
        fun onPropertiesClicked()
        fun onUsageClicked()
        fun onSortByClicked()
        fun onSettingsClicked()

        fun onCopyClicked()
        fun onPasteClicked()
        fun onDeleteClicked()
        fun onSearchClcked()
        fun onOpenClicked()

        fun addLocalSource(name: String)
        fun addLocalSources()
    }

    interface FragmentView : BaseView<FragmentPresenter> {
        fun initViews()
        fun showRootFolder()
        fun toggleLoading()
        fun toggleConnectButton()
        fun toggleLogo()
        fun createBreadcrumb(file: SourceFile): android.view.View
        fun pushBreadcrumb(crumb: android.view.View)
        fun popBreadcrumb()
    }

    interface FragmentPresenter : BasePresenter<FragmentView> {


        fun connect(onComplete: ConnectListener)
        fun onFileSelected(file: SourceFile, context: Context?)
        fun openFile(file: SourceFile, context: Context?)
        fun getSourceObj(): Source
        fun navigateToBreadcrumb(file: SourceFile)
        fun loadRootFolder(onNext: (files: List<SourceFile>) -> Unit,
                           onError: (error: Throwable) -> Unit,
                           onComplete: () -> Unit)
    }

    interface FileRowView {
        fun setPreviewImage(path: String)
        fun setFilename(name: String)
        fun setSelected(selected: Boolean)
    }

    interface FilePresenter {
        fun onItemSelected(position: Int, context: Context?)
        fun getFileCount(): Int
        fun setCurrentDirectory(fileList: List<SourceFile>)
        fun bindViewForPosition(position: Int, fileRowView: FileRowView)
    }
}

typealias ConnectListener = () -> Unit
