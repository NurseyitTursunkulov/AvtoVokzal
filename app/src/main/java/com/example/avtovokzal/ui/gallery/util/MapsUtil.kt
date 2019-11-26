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
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.avtovokzal.R
import com.example.avtovokzal.ui.gallery.MapsFragment
import com.example.avtovokzal.ui.gallery.MapsFragmentDirections
import com.example.permissionlib.checkPermission
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
 val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 2

fun MapsFragment.displayOnMapLocation(
    googleMap: GoogleMap?,
    location: Location,
    onLocationSelected:(LatLng)->Unit
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
            Log.d("Nurs" , "location ${cameraPosition.target}")
            val markerOptions = MarkerOptions().position(cameraPosition.target)
                .icon(bitmapDescriptorFromVector(requireContext(),R.drawable.ic_baseline_emoji_people_24))
            addMarker(markerOptions)
            onLocationSelected(cameraPosition.target)
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

fun MapsFragment.requestLocation(mapFragment: SupportMapFragment?,
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

fun MapsFragment.checkLocationPermission(onGranted: () -> Unit){
    checkPermission(
        fragment = this,
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        requestCode = MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION,
        onGranted = { onGranted() }
    )
}

 fun MapsFragment.navigateToGalleryFragment(view: View) {
    NavHostFragment.findNavController(this)
        .navigate(MapsFragmentDirections.actionMapsFragmentToNavGallery())

    val controller = Navigation.findNavController(view)
    controller.popBackStack(R.id.mapsFragment, true)
}