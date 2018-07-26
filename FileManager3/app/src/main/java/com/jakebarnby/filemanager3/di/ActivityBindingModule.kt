package com.jakebarnby.filemanager3.di

import com.jakebarnby.filemanager3.MainActivity
import com.jakebarnby.filemanager3.sources.SourceModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [(SourceModule::class)])
    internal abstract fun mainActivity(): MainActivity
}