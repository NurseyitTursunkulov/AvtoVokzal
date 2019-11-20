package com.example.permissionlib

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

val MY_PERMISSIONS_REQUEST_ACCESS_CAMERA = 3
fun checkPermission(
    fragment:Fragment,
    onGranted: () -> Unit,
    permission: String,
    requestCode: Int) {
    if (ContextCompat.checkSelfPermission(
            fragment.requireContext(),
            permission
        )
        != PackageManager.PERMISSION_GRANTED
    ) {
        Log.d("Nurs", "not granted")
        // Permission is not granted
        // Should we show an explanation?
        if (shouldShowRequestPermissionRationale(
                fragment.requireActivity(),
                permission
            )
        ) {
            Log.d("Nurs", " show an explanation ")
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed, we can request the permission.
            fragment.requestPermissions(
                arrayOf(permission),
                requestCode
            )
            Log.d("Nurs", "  request the permission")
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    } else {
        // Permission has already been granted
        Log.d("Nurs", "else")
        onGranted()
    }
}

fun onRequestPermissionsResult(
    requestCodeFromSystem: Int,
    grantResults: IntArray,
    requestCode : Int,
    onGranted: () -> Unit) {
    when (requestCodeFromSystem) {
        requestCode -> {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                android.util.Log.d("Nurs", "onRequestPermissionsResult")
                onGranted()
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                android.util.Log.d("Nurs", "onRequestPermissionsResult else ")

            }
            return
        }

        // Add other 'when' lines to check for other
        // permissions this app might request.
        else -> {
            // Ignore all other requests.
            Log.d("Nurs","else checkOnRequestPermissionsResult")
        }
    }
}
