package com.jakebarnby.filemanager3.sources.core

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SourcePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments: Array<Fragments> = Fragments.values()

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].title
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position].fragment
    }

    override fun getCount(): Int {
        return fragments.size
    }

}