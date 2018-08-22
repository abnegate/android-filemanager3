package com.jakebarnby.filemanager3.sources.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.jakebarnby.filemanager3.util.Constants.Sources.LOCAL
import com.jakebarnby.filemanager3.util.isMediaFile
import java.io.File
import java.io.Serializable

/**
 * Created by Jake on 6/5/2017.
 */

@Entity(tableName = "files")
class SourceFile() : Serializable {

    @PrimaryKey
    var id = 0L
    var parentId = 0L
    var path: String? = null
    var name: String? = null
    var sourceName: String? = null
    var sourceType: SourceType? = null
    var thumbnailLink: String? = null
    var isDirectory: Boolean = false
    var isHidden: Boolean = false
    var isMediaFile: Boolean = false
    var size: Long = 0
    var modifiedAt: Long = 0

    constructor(file: File, fileParentId: Long) : this() {
        id = file.hashCode().toLong()
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
        isMediaFile = file.isMediaFile()
    }
}
