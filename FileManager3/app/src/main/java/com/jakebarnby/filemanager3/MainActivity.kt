package com.jakebarnby.filemanager3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.jakebarnby.filemanager3.sources.core.SourceContract
import com.jakebarnby.filemanager3.sources.core.SourcePagerAdapter
import com.jakebarnby.filemanager3.sources.core.SourcePresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SourceContract.View {

    @Inject lateinit var presenter: SourcePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        view_pager.adapter = SourcePagerAdapter(supportFragmentManager)
        view_pager.offscreenPageLimit = (view_pager.adapter as SourcePagerAdapter).count

        tabs.setupWithViewPager(view_pager)
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
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

    override fun showErrorWithActionSnackbar(error: String, listener: View.OnClickListener) {
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
}
