package com.jakebarnby.filemanager3.sources.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.jakebarnby.filemanager3.data.helpers.LocalFileIdHelper
import com.jakebarnby.filemanager3.util.Constants.Sources.LOCAL
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.Serializable

/**
 * Created by Jake on 6/5/2017.
 */

@Entity(tableName = "files")
class SourceFile() : Serializable {

    constructor(file: File, fileParentId: Int) : this() {
        LocalFileIdHelper()
            .getId(file)
            .subscribeOn(Schedulers.io())
            .doOnError {
                //TODO: Error handling
            }
            .subscribe { fileId ->
                id = fileId
            }

        parentId = fileParentId
        path = file.absolutePath
        name = file.name
        sourceName = LOCAL
        sourceType = SourceType.LOCAL
        thumbnailLink = path
        isDirectory = file.isDirectory
        isHidden = file.name.contains('.')
        size = file.length()
        modifiedAt = file.lastModified()
    }

    @PrimaryKey
    var id: Long = 0
    var parentId: Int = 0
    var path: String? = null
    var name: String? = null
    var sourceName: String? = null
    var sourceType: SourceType? = null
    var thumbnailLink: String? = null
    var isDirectory: Boolean = false
    var isHidden: Boolean = false
    var size: Long = 0
    var modifiedAt: Long = 0
}
