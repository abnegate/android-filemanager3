package com.jakebarnby.filemanager3.sources.core

import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import com.jakebarnby.filemanager3.core.BasePresenter
import com.jakebarnby.filemanager3.core.BaseView
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
        fun onPaseClicked()
        fun onDeleteClicked()
        fun onSearchClcked()
        fun onOpenClicked()

        fun addLocalSource(name: String)
        fun addLocalSources()
    }

    interface FragmentView {
        fun toggleLoading()
        fun toggleConnectButton()
        fun createBreadcrumb(file: SourceFile): android.view.View
        fun pushBreadcrumb(crumb: android.view.View)
        fun popBreadcrumb()
    }

    interface FragmentPresenter : BasePresenter<FragmentView> {
        fun connect(onComplete: ConnectListener)
        fun openFile(file: SourceFile)
        fun navigateToBreadcrumb(file: SourceFile)
    }

    interface SourceFilePresenter
}

typealias ConnectListener = () -> Unit
