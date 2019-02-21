package com.jakebarnby.filemanager3.data.helpers

import com.jakebarnby.filemanager3.util.getBytes
import io.reactivex.Single
import java.io.File
import java.io.IOException
import java.util.zip.CRC32

class LocalFileIdHelper : IdHelper<File> {

    private companion object {
        val crc32 = CRC32()
    }

    override fun getId(obj: File): Long {
        return obj.hashCode().toLong()
//        try {
//            crc32.reset()
//            crc32.update(obj.hashCode())
//            return crc32.value
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
    }
}