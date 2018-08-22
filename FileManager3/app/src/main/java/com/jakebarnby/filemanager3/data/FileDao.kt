package com.jakebarnby.filemanager3.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.jakebarnby.filemanager3.sources.models.SourceFile
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Data Access Object for the users table.
 */
@Dao
interface FileDao {

    @Query("SELECT * FROM files")
    fun getFiles(): Flowable<List<SourceFile>>

    /**
     * Get a file by id.
     * @return the file from the table with a specific id.
     */
    @Query("SELECT * FROM files WHERE id = :id")
    fun getFileById(id: Long): Maybe<SourceFile>

    /**
     * Get a file by source and id.
     * @return the file from the table with a specific id.
     */
    @Query("SELECT * FROM files WHERE sourceName = :sourceName AND id = :id")
    fun getFileByIdAndSource(id: Long, sourceName: String): Maybe<SourceFile>

    /**
     *
     */
    @Query("SELECT * FROM files WHERE sourceName = :sourceName ORDER BY name ASC")
    fun getFilesBySource(sourceName: String): Flowable<List<SourceFile>>

    /**
     *
     */
    @Query("SELECT * FROM files WHERE parentId = :parentId AND sourceName = :sourceName")
    fun getFolderBySource(parentId: Long, sourceName: String): Flowable<List<SourceFile>>

    /**
     * Insert a file in the database. If the file already exists, ignore it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFile(file: SourceFile)

    /**
     * Insert a file in the database. If the file already exists, ignore it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFiles(fileList: List<SourceFile>)

    /**
     * Delete all files.
     */
    @Query("DELETE FROM files")
    fun deleteAllFiles()
}