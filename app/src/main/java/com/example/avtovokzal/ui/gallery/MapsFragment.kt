package com.example.avtovokzal.ui.gallery

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.avtovokzal.MainActivity
import com.example.avtovokzal.R
import com.example.avtovokzal.ui.gallery.util.*
import com.example.permissionlib.onRequestPermissionsResult

import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_maps.*

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

        (activity as MainActivity).fab.visibility = View.INVISIBLE
        okFab.setOnClickListener {
            navigateToGalleryFragment(view)
        }
        checkLocationPermission(
            onGranted = { requestLocation(mapFragment, ::displayOnMapLocation) }
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.d("Nurs", "onRequestPermissionsResult")
        onRequestPermissionsResult(
            requestCodeFromSystem = requestCode,
            grantResults = grantResults,
            onGranted = {
                requestLocation(mapFragment, ::displayOnMapLocation)
            },
            requestCode = MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION
        )
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).fab.visibility = View.VISIBLE
    }
}