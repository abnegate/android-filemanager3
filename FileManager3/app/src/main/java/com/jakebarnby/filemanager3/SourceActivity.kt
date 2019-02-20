package com.jakebarnby.filemanager3

import android.os.Bundle
import com.jakebarnby.filemanager3.sources.core.Fragments
import com.jakebarnby.filemanager3.sources.core.SourceContract
import com.jakebarnby.filemanager3.sources.core.SourceFragmentPresenter
import com.jakebarnby.filemanager3.sources.core.SourcePagerAdapter
import com.sembozdemir.permissionskt.askPermissions
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class SourceActivity : DaggerAppCompatActivity(), SourceContract.ActivityView {

    @Inject
    lateinit var presenter: SourceContract.ActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewPager.adapter = SourcePagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = Fragments.values().size - 1

        tabs.setupWithViewPager(viewPager)
        checkPermissions()
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
    }

    override fun onStop() {
        presenter.unsubscribe()
        super.onStop()
    }

    private fun checkPermissions() {
        askPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) {
            onGranted {
                //TODO: Permission granted, load files
            }

            onDenied {
                //TODO: Show permissions is required to do anything and request again
            }

            onShowRationale {
                showErrorDialog()
            }

            onNeverAskAgain {
                showErrorWithActionSnackbar("Never ask again.") {
                    //TODO: Open settings permissions
                }
            }
        }
    }

    override fun showViewAsDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCreateFolderDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCreateZipDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPropertiesDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUsageDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLogoutDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSortByDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSettingsDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAd() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupFloatingMenu() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toggleFloatingMenu(enabled: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorSnackbar(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessageSnackbar(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorWithActionSnackbar(error: String, listener: () -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageScrollStateChanged(state: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageSelected(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        val curFragPresenter = Fragments
            .values()[viewPager.currentItem]
            .fragment
            .fragmentPresenter

        val source = (curFragPresenter as SourceFragmentPresenter).source
        val parentId = source.currentFolderParentId

        curFragPresenter
            .breadcrumbPresenter
            .popBreadcrumb()

        source.disposables.add(source.fileDao!!
            .getFileById(parentId)
            .subscribeOn(Schedulers.io())
            .subscribe {
                curFragPresenter.onFileSelected(it, this)
            })
    }
}
