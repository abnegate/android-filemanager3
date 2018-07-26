package com.jakebarnby.filemanager3.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.jakebarnby.filemanager3.sources.models.SourceFile

@Database(entities = arrayOf(SourceFile::class), version = 1)
abstract class FileDatabase : RoomDatabase() {

    abstract fun fileDao(): FileDao

    companion object {

        @Volatile private var INSTANCE: FileDatabase? = null

        fun getInstance(context: Context): FileDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        FileDatabase::class.java, "filemanager.db")
                        .build()
    }
}