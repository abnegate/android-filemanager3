package com.jakebarnby.filemanager3.data

import android.content.Context
import com.jakebarnby.filemanager3.di.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    @ActivityScoped
    fun provideFileDao(context: Context): FileDao {
        return FileDatabase.getInstance(context).fileDao()
    }
}