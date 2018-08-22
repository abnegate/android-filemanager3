package com.jakebarnby.filemanager3.util

import android.webkit.MimeTypeMap
import com.jakebarnby.filemanager3.util.Constants.MIME_LIST
import java.io.File
import java.io.FileInputStream
import java.io.IOException

@Throws(IOException::class)
fun File.getBytes(): ByteArray {
    if (isFile) {
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
    } else {
        return ByteArray(0)
    }
}

fun File.isMediaFile(): Boolean {
    if (isDirectory || extension.isEmpty()) return false

    val type: String? = MimeTypeMap
        .getSingleton()
        .getMimeTypeFromExtension(extension)

    type?.let {
        return MIME_LIST.contains(it)
    }
    return false
}