package com.jakebarnby.filemanager3.di

import com.jakebarnby.filemanager3.SourceActivity
import com.jakebarnby.filemanager3.sources.SourceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [(SourceModule::class)])
    internal abstract fun sourceActivity(): SourceActivity
}