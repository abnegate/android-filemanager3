package com.jakebarnby.filemanager3.data

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
open class DatabaseModule {

    @Provides
    fun provideFileDao(context: Context): FileDao {
        return FileDatabase.getInstance(context).fileDao()
    }
}