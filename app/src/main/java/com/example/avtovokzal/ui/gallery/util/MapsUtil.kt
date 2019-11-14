package com.example.avtovokzal.ui.gallery.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.avtovokzal.R
import com.example.avtovokzal.ui.gallery.MapsFragment
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
internal val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 2

fun MapsFragment.displayOnMapLocation(
    googleMap: GoogleMap?,
    location: Location
) {
    googleMap?.apply {
            isMyLocationEnabled = true
            addMarker(
                MarkerOptions().position(
                    LatLng(
                        location.latitude,
                        location.longitude
                    )
                ).title("Marker in Sydney")
                    . draggable(false)
            )
            moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        location.latitude,
                        location.longitude
                    ),
                    11.5F
                )
            )
            uiSettings.isCompassEnabled=true
        setOnCameraMoveListener{
            clear()
            // display imageView
            imgLocationPinUp?.visibility = View.VISIBLE
        }
        setOnCameraIdleListener{
            imgLocationPinUp?.visibility = View.GONE
            // customizing map marker with a custom icon
            // and place it on the current map camera position
            val markerOptions = MarkerOptions().position(cameraPosition.target)
                .icon(bitmapDescriptorFromVector(requireContext(),R.drawable.ic_baseline_emoji_people_24))
            addMarker(markerOptions)
        }
    }
}

private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}

fun MapsFragment.getLocation(mapFragment: SupportMapFragment?,
                onLocationRecieved: (m: GoogleMap, location: Location) -> Unit)
{
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            // Got last known location. In some rare situations this can be null.
            Log.d("Nurs", "$location")
            location?.let {
                mapFragment?.getMapAsync { googleMap : GoogleMap ->
                    onLocationRecieved(
                        googleMap, location
                    )
                }
            }
        }
        .addOnCompleteListener {
            Log.d("Nurs", "map ${it.result}")
        }
}

 fun MapsFragment.checkLocationPermission(onGranted: () -> Unit) {
    if (ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        != PackageManager.PERMISSION_GRANTED
    ) {
        Log.d("Nurs", "not granted")
        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Log.d("Nurs", " show an explanation ")
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION
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

fun MapsFragment.checkOnRequestPermissionsResult(
    requestCode: Int,
    grantResults: IntArray,
    onGranted: () -> Unit) {
    when (requestCode) {
        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION -> {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                Log.d("Nurs", "onRequestPermissionsResult")
                onGranted()
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Log.d("Nurs", "onRequestPermissionsResult else ")

            }
            return
        }

        // Add other 'when' lines to check for other
        // permissions this app might request.
        else -> {
            // Ignore all other requests.
        }
    }
}