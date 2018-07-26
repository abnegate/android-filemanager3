package com.jakebarnby.filemanager3.sources.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.jakebarnby.filemanager3.util.Constants.Sources.LOCAL
import java.io.File
import java.io.Serializable
import java.util.*

/**
 * Created by Jake on 6/5/2017.
 */

@Entity(tableName = "files")
class SourceFile : Serializable {

    constructor(file: File, fileParentId: Int) {
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
    val id: Int = Random().nextInt(Int.MAX_VALUE)
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
