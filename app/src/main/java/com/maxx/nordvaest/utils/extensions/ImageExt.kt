package com.maxx.nordvaest.utils.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

/**
 * Created by rakezb on 13 August, 2018.
 */
fun resizeBitmap(image: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
    var image = image
    if (maxHeight > 0 && maxWidth > 0) {
        val width = image.width
        val height = image.height
        val ratioBitmap = width.toFloat() / height.toFloat()
        val ratioMax = maxWidth.toFloat() / maxHeight.toFloat()

        var finalWidth = maxWidth
        var finalHeight = maxHeight
        if (ratioMax > ratioBitmap) {
            finalWidth = (maxHeight.toFloat() * ratioBitmap).toInt()
        } else {
            finalHeight = (maxWidth.toFloat() / ratioBitmap).toInt()
        }
        image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true)
        return image
    } else {
        return image
    }
}

/**
 * create scale bitmap and return file
 */
fun createScaledBitmapFile(filePath: String, file: File): File {
//        val imageBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(filePath), 320, 480, false)
    val originalBitmap = BitmapFactory.decodeFile(filePath)
    var newBitmap: Bitmap? = null
    if (originalBitmap != null)
        newBitmap = resizeBitmap(originalBitmap, 360, 360)
    try {
        // Compress the bitmap and save in jpg format
        val stream: OutputStream = FileOutputStream(file)
        newBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.flush()
        stream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return file
}

/**
 * get absolute image path
 */
fun Fragment.getSelectedImagePath(photoUri: Uri): Any? {
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = this.activity?.contentResolver?.query(photoUri, projection, null, null, null)
    if (cursor == null)
        return null
    val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
    cursor.moveToFirst()
    val path = cursor.getString(column_index)
    cursor.close()
    return path
}


