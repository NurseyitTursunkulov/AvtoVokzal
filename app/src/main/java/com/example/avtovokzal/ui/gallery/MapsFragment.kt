package com.example.avtovokzal.ui.gallery

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.avtovokzal.R
import com.example.avtovokzal.ui.gallery.util.checkLocationPermission
import com.example.avtovokzal.ui.gallery.util.displayOnMapLocation
import com.example.avtovokzal.ui.gallery.util.getLocation
import com.example.avtovokzal.ui.gallery.util.checkOnRequestPermissionsResult
import com.google.android.gms.location.FusedLocationProviderClient

import com.google.android.gms.maps.SupportMapFragment

class MapsFragment : Fragment() {

    internal var mapFragment: SupportMapFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        checkLocationPermission(
            onGranted = { getLocation(mapFragment, ::displayOnMapLocation) }
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        checkOnRequestPermissionsResult(requestCode, grantResults,
            onGranted = {
                getLocation(mapFragment, ::displayOnMapLocation)
            })
    }

}