package com.iu6_mamykin.wonderpicturesclient.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import java.io.File
import java.io.FileOutputStream

suspend fun downloadImage(context: Context, url: String) {
    val loader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(url)
        .build()
    val result = (loader.execute(request) as SuccessResult).drawable
    val bitmap = result.toBitmap()
    saveBitmapToFile(context, bitmap)
}

fun saveBitmapToFile(context: Context, bitmap: Bitmap) {
    val timestamp = System.currentTimeMillis()
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "downloaded_image$timestamp.jpg")
    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
    }
}