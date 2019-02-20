package com.jakebarnby.filemanager3.sources

import com.jakebarnby.filemanager3.di.ActivityScoped
import com.jakebarnby.filemanager3.sources.core.SourceActivityPresenter
import com.jakebarnby.filemanager3.sources.core.SourceContract
import dagger.Binds
import dagger.Module

@Module
abstract class SourceModule {
    @ActivityScoped
    @Binds
    internal abstract fun providePresenter(presenter: SourceActivityPresenter): SourceContract.ActivityPresenter
}
