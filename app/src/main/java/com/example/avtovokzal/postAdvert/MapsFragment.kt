package com.example.avtovokzal.postAdvert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.avtovokzal.MainActivity
import com.example.avtovokzal.R
import com.example.avtovokzal.postAdvert.util.*
import com.example.avtovokzal.util.Event
import com.example.permissionlib.onRequestPermissionsResult
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_maps.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel


class MapsFragment : Fragment() {

    internal var mapFragment: SupportMapFragment? = null
    val postAdvertViewModel: PostAdvertViewModel by sharedViewModel()
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
                chooseYourLocation()
            }
        )
    }

    private fun chooseYourLocation() {
        viewLifecycleOwner.lifecycleScope.launch {
            val location = requestLocation()
            val map = mapFragment?.getMap()
            location?.let { location ->
                displayOnMap(map, location,
                    onNewLocationSelected = { newLocation ->
                        postAdvertViewModel.advertModel.location
                            .postValue(LatLng(newLocation.latitude, newLocation.longitude))

                        getAdress(newLocation)?.let { address ->
                            postAdvertViewModel.advertModel.address.postValue(Event(address))
                        }
                    }
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        onRequestPermissionsResult(
            requestCodeFromSystem = requestCode,
            grantResults = grantResults,
            onGranted = {
                chooseYourLocation()
            },
            requestCode = MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION
        )
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).fab.visibility = View.VISIBLE
    }
}