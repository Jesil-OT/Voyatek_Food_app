package com.assessment.voyatek.core.presentation.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PhotoManager {
    fun takePhoto(
        context: Context,
        cameraLauncher: ActivityResultLauncher<Uri>,
        permissionLauncher: ActivityResultLauncher<String>,
        onCapturePhotoUri: (Uri?) -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            val file = createImageFile(context)
            val photoUri: Uri? = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
            photoUri?.let { uri -> cameraLauncher.launch(uri) }
            onCapturePhotoUri(photoUri)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    fun uploadPhoto(
        uploadImageLauncher: ActivityResultLauncher<PickVisualMediaRequest>
    ) {
        uploadImageLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }


    private fun createImageFile(context: Context): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(null)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
    }

    fun uriToFile(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "temp_image")
        inputStream?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return file
    }


    fun getMediaTypeForFile(file: File): String {
        return when (file.extension.lowercase()) {
            "png" -> "image/png"
            "jpg", "jpeg" -> "image/jpeg"
            "webp" -> "image/webp"
            else -> "application/octet-stream" // Default for unknown types
        }
    }

}