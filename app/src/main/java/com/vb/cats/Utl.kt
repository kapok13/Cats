package com.vb.cats

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

fun shouldRequestExternalStoragePermissions(context: Context): Pair<Boolean, Array<String>> {
    val permissions = mutableListOf<String>()
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    if ((ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED)) {
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    return Pair(permissions.isNotEmpty(), permissions.toTypedArray())
}

fun saveImageToStorage(context: Context, bitmap: Bitmap): Boolean {
    var isSaved = true
    val byteArrayOutputStream = ByteArrayOutputStream()
    val outputStream = FileOutputStream(
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "${System.currentTimeMillis()}.jpeg")
    )
    try {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        outputStream.write(byteArrayOutputStream.toByteArray())
    } catch (e: Exception) {
        Log.d(context.packageName, e.toString())
        isSaved = false
    } finally {
        outputStream.close()
    }
    return isSaved
}

fun saveImageToStorage(context: Context, url: String, result: ((isSuccess: Boolean) -> Unit)?) {
    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                val isSaved = saveImageToStorage(context, resource)
                result?.invoke(isSaved)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
}
