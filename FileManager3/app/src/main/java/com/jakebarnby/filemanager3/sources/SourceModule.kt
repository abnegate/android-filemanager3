package com.jakebarnby.filemanager3.sources

import com.jakebarnby.filemanager3.di.ActivityScoped
import com.jakebarnby.filemanager3.sources.core.SourceContract
import com.jakebarnby.filemanager3.sources.core.SourcePresenter
import dagger.Binds
import dagger.Module

@Module
abstract class SourceModule {
    @ActivityScoped
    @Binds
    internal abstract fun providePresenter(presenter: SourcePresenter): SourceContract.Presenter
}
