package com.example.sumer.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.PermissionChecker.PERMISSION_DENIED
import androidx.core.content.PermissionChecker.checkCallingOrSelfPermission

class PermissionCheck(val context: Context) {
    fun requestRecordAudioPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val requiredPermission = Manifest.permission.RECORD_AUDIO
            // If the user previously denied this permission then show a message explaining why
            // this permission is needed
            if (checkCallingOrSelfPermission(context, requiredPermission) ===
                PERMISSION_DENIED) {
                requestPermissions(context as Activity, arrayOf(requiredPermission), 101)
            }
        }
    }
}