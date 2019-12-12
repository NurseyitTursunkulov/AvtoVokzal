package com.example.avtovokzal.ui.gallery.util

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TimePicker
import androidx.navigation.fragment.NavHostFragment
import com.example.avtovokzal.ui.gallery.GalleryFragment
import com.example.avtovokzal.ui.gallery.GalleryFragmentDirections
import com.example.permissionlib.MY_PERMISSIONS_REQUEST_ACCESS_CAMERA
import com.example.permissionlib.checkPermission
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_gallery.*
import java.util.*

fun GalleryFragment.selectTime(ontimeSelected: (year:Int, month:Int, day:Int, hour:Int, min:Int) -> Unit) {
    var mYear : Int = 0
    var mMonth : Int = 0
    var mDay : Int = 0
    val calendar = Calendar.getInstance();
    val timePickerDialog = TimePickerDialog(
        requireContext(),
        object : TimePicker.OnTimeChangedListener,
            TimePickerDialog.OnTimeSetListener {
            override fun onTimeChanged(
                view: TimePicker?,
                hourOfDay: Int,
                minute: Int
            ) {
            }
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//                galleryViewModel.time.postValue(DateModel(hourOfDay, minute))
//                Log.d("Nurs","time selected  $hourOfDay $minute")
//                val sfd = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                ontimeSelected(mYear, mMonth, mDay, hourOfDay,minute)
//                sfd.format(Date( mYear, mMonth, mDay, hourOfDay,minute))
            }

        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true
    )

    val datapicker = DatePickerDialog(
        requireContext(),
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            mYear = year
            mMonth = month
            mDay = dayOfMonth
            Log.d("Nurs","date selected $year $month $dayOfMonth")
            timePickerDialog.show();
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    datapicker.show()
}

fun GalleryFragment.checkCameraPermission(onGranted: () -> Unit) {
    checkPermission(
        fragment = this,
        onGranted = {
            onGranted()
        },
        permission = Manifest.permission.CAMERA,
        requestCode = MY_PERMISSIONS_REQUEST_ACCESS_CAMERA
    )
}

fun GalleryFragment.navigateToMaps() {
    NavHostFragment.findNavController(this)
        .navigate(GalleryFragmentDirections.actionNavGalleryToMapsFragment())
}


fun GalleryFragment.showSuccessDialog(it: String) {
    MaterialAlertDialogBuilder(context)
        .setTitle(it)
        .setPositiveButton("Ok", null)
        .show()
}


fun GalleryFragment.showProgressBar(state: Boolean) {
    if (state) progressBar2.visibility = View.VISIBLE
    else progressBar2.visibility = View.INVISIBLE
}


fun GalleryFragment.dispatchTakePictureIntent() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }
}


fun GalleryFragment.initAutoCompleteTextViewForCities(layoutId:Int,citiesInKG:List<String>) {
    val adapter: ArrayAdapter<String> =
        ArrayAdapter<String>(requireContext(),layoutId, citiesInKG)
    fromTV.threshold = 1;
    fromTV.setAdapter(adapter)
    toTV.threshold = 1
    toTV.setAdapter(adapter)
}