package com.example.weightogo

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ofPattern
import java.util.Locale

class PhotoRepo {
    private var context: Context? = null

    fun PhotoRepo(context: Context?) {
        this.context = context
    }

    // saves file and returns a path
    fun savePhotoToDisk(photo: Bitmap): String {
        val wrapper = ContextWrapper(context)
        val photosDir = wrapper.getDir("photos", Context.MODE_PRIVATE)
        // use a file path that is unique down to the current second
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ofPattern("yyyy-MM-dd-HH-mm-ss", Locale.ENGLISH)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val currentTimestampFile = formatter.format(LocalDateTime.now()) + ".jpg"
        val photoFile = File(photosDir, currentTimestampFile)
        // attempt to save file to disk
        try {
            FileOutputStream(photoFile).use { stream ->
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                stream.flush()
                return photoFile.absolutePath
            }
        } catch (ignored: IOException) {
            Log.w(TAG, "Failed to save file: " + photoFile.absolutePath)
            return ""
        }
    }

    // delete the photo from disk
    fun deletePhotoFromDisk(photoPath: String?) {
        val photoFile = File(photoPath)
        if (photoFile.exists()) {
            if (!photoFile.delete()) {
                Log.w(TAG, "Failed to delete file: " + photoFile.absolutePath)
            }
        }
    }

    companion object {
        /* Cite Seifken 2024
     * Storage for persisting photo files.
     * Stores files on disk privately and generates unique filenames for them.
     */
        private const val TAG = "PhotoRepository"
    }
}
