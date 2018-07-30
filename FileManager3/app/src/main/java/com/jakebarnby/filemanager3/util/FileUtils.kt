package com.jakebarnby.filemanager3.util

import java.io.File
import java.io.FileInputStream
import java.io.IOException

object FileUtils {

    @Throws(IOException::class)
    fun bytesFromFile(file: File): ByteArray {
        val length = file.length()
        if (length > Integer.MAX_VALUE) {
            throw IOException("File is too large!")
        }

        val bytes = ByteArray(length.toInt())
        var offset = 0
        var numRead: Int

        val inStream = FileInputStream(file)
        inStream.use { stream ->
            while (offset < bytes.size) {
                numRead = stream.read(bytes, offset, bytes.size - offset)
                if (numRead >= 0) {
                    offset += numRead
                }
            }
        }
        if (offset < bytes.size) {
            throw IOException("Could not completely read file " + file.name)
        }
        return bytes
    }
}