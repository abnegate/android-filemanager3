package com.jakebarnby.filemanager3.di

import android.app.Application
import com.jakebarnby.filemanager3.core.FileManagerApplication
import com.jakebarnby.filemanager3.data.DatabaseModule
import com.jakebarnby.filemanager3.sources.SourceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    SourceModule::class,
    DatabaseModule::class])
interface AppComponent : AndroidInjector<FileManagerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}

