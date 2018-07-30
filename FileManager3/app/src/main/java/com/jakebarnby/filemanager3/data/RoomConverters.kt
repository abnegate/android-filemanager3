package com.jakebarnby.filemanager3.data

import android.arch.persistence.room.TypeConverter
import com.jakebarnby.filemanager3.sources.models.SourceType

class RoomConverters {

    @TypeConverter
    fun sourceTypeToString(type: SourceType): String = type.toString()

    @TypeConverter
    fun stringToSourceType(type: String): SourceType = SourceType.valueOf(type)
}