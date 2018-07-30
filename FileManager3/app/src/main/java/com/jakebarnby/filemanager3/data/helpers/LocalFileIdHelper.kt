package com.jakebarnby.filemanager3.data.helpers

import io.reactivex.Single
import java.io.File
import java.io.IOException
import java.util.zip.CRC32

import com.jakebarnby.filemanager3.util.getBytes

class LocalFileIdHelper: FileIdHelper<File> {

    companion object {
        val crc32 = CRC32()
    }

    override fun getId(file: File): Single<Long> {
        return Single.create {
            try {
                val bytes = file.getBytes()
                crc32.reset()
                crc32.update(bytes)
                it.onSuccess(crc32.value)
            } catch (e: IOException) {
                it.onError(e)
            }
        }
    }
}