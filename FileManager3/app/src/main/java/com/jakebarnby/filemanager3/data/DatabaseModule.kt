package com.jakebarnby.filemanager3.data

import android.app.Application
import com.jakebarnby.filemanager3.di.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    @ActivityScoped
    fun provideFileDao(application: Application): FileDao {
        return FileDatabase.getInstance(application).fileDao()
    }
}