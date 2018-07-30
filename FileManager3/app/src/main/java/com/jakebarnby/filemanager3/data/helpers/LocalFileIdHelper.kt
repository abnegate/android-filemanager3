package com.jakebarnby.filemanager3.data.helpers

import io.reactivex.Single
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.zip.CRC32

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

    @Throws(IOException::class)
    private fun File.getBytes(): ByteArray {
        val length = length()
        if (length > Integer.MAX_VALUE) {
            throw IOException("File is too large!")
        }

        val bytes = ByteArray(length.toInt())
        var offset = 0
        var numRead: Int

        val inStream = FileInputStream(this)
        inStream.use { stream ->
            while (offset < bytes.size) {
                numRead = stream.read(bytes, offset, bytes.size - offset)
                if (numRead >= 0) {
                    offset += numRead
                }
            }
        }
        if (offset < bytes.size) {
            throw IOException("Could not completely read file $name")
        }
        return bytes
    }
}