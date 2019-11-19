package com.example.avtovokzal.ui.gallery

import android.Manifest
import android.R
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.avtovokzal.ui.gallery.util.*
import com.example.permissionlib.MY_PERMISSIONS_REQUEST_ACCESS_CAMERA
import com.example.permissionlib.checkPermission
//import com.example.permissionlib.checkCameraPermission
import com.example.permissionlib.onRequestPermissionsResult
import com.example.avtovokzal.R as T
import kotlinx.android.synthetic.main.fragment_gallery.*


class GalleryFragment : Fragment() {

    internal lateinit var galleryViewModel: GalleryViewModel
    var languagesA =
        arrayOf(
            "Исфана",
            "Кадамжай",
            "Баткен",
            "Aксы",
            "Ала-Бука",
            "Бишкек",
            "Ош",
            "Жалал-Абад",
            "JSON"
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        return inflater.inflate(T.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set the number of characters the user must type before the drop down list is shown
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), R.layout.select_dialog_singlechoice, languagesA)
        fromTV.threshold = 1;
        fromTV.setAdapter(adapter)
        toTV.threshold = 1
        toTV.setAdapter(adapter)
        calendarBtn.setOnClickListener {
            selectTime();
        }
        driving_licience_btn.setOnClickListener {
            checkPermission(
                fragment = this,
                onGranted = {
                    dispatchTakePictureIntent()
                },
                permission = Manifest.permission.CAMERA,
                requestCode = MY_PERMISSIONS_REQUEST_ACCESS_CAMERA
            )
        }
        location_button.setOnClickListener {
            NavHostFragment.findNavController(this@GalleryFragment)
                .navigate(GalleryFragmentDirections.actionNavGalleryToMapsFragment())
        }
    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
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
