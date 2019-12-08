package com.example.avtovokzal.ui.gallery

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.avtovokzal.MainActivity
import com.example.avtovokzal.R
import com.example.avtovokzal.core.domain.MyLatLng
import com.example.avtovokzal.ui.gallery.util.*
import com.example.permissionlib.onRequestPermissionsResult

import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_maps.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MapsFragment : Fragment() {

    internal var mapFragment: SupportMapFragment? = null
    val galleryViewModel: GalleryViewModel by sharedViewModel()
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
            onGranted = {
                requestLocation(mapFragment) { googleMap, location ->
                    displayOnMap(googleMap, location,
                        onNewLocationSelected = { newLocation ->
                            galleryViewModel.advertModel.location
                                .postValue(MyLatLng(newLocation.latitude,newLocation.longitude))
                        }
                    )
                }
            }
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
                requestLocation(mapFragment) { googleMap, location ->
                    displayOnMap(googleMap, location,
                        onNewLocationSelected = { newLocation ->
                            galleryViewModel.advertModel.location
                                .postValue(MyLatLng(newLocation.latitude,newLocation.longitude))
                        }
                    )
                }
            },
            requestCode = MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION
        )
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).fab.visibility = View.VISIBLE
    }
}