package com.example.avtovokzal.ui.gallery

import android.R
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.avtovokzal.databinding.FragmentGalleryBinding
import com.example.avtovokzal.ui.gallery.util.*
import com.example.avtovokzal.util.EventObserver
import com.example.permissionlib.MY_PERMISSIONS_REQUEST_ACCESS_CAMERA
//import com.example.permissionlib.checkCameraPermission
import com.example.permissionlib.onRequestPermissionsResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.avtovokzal.R as T
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.koin.android.viewmodel.ext.android.getSharedViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class GalleryFragment : Fragment() {

    val galleryViewModel: GalleryViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflate<FragmentGalleryBinding>(inflater, T.layout.fragment_gallery, container, false)
        binding.viewModel = galleryViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAutoCompleteTextViewForCities( R.layout.select_dialog_singlechoice, citiesInKG.toList())
        calendarBtn.setOnClickListener {
            selectTime(
                ontimeSelected = { year: Int, month: Int, day: Int, hour: Int, min: Int ->
                    galleryViewModel.onTimeSelected(year,month,day,hour,min)
                }
            )
        }
        driving_licience_btn.setOnClickListener {
            checkCameraPermission (onGranted = {
                dispatchTakePictureIntent()
            } )
        }
        location_button.setOnClickListener {
            navigateToMaps()
        }
        galleryViewModel.dialog.observe(this,EventObserver{
             showSuccessDialog(it);
        })
        galleryViewModel.spinner.observe(this,Observer{state:Boolean->
            showProgressBar(state)
        })
        galleryViewModel.advertModel.date.observe(this, Observer {

        })
    }

    val REQUEST_IMAGE_CAPTURE = 1

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        onRequestPermissionsResult(
            requestCodeFromSystem = requestCode,
            grantResults = grantResults,
            requestCode = MY_PERMISSIONS_REQUEST_ACCESS_CAMERA,
            onGranted = {
                dispatchTakePictureIntent()
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView2.setImageBitmap(imageBitmap)

        }
    }


}
