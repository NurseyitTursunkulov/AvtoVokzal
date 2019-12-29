package com.example.avtovokzal.postAdvert

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.avtovokzal.databinding.FragmentGalleryBinding
import com.example.avtovokzal.postAdvert.util.*
import com.example.avtovokzal.util.EventObserver
import com.example.permissionlib.MY_PERMISSIONS_REQUEST_ACCESS_CAMERA
import com.example.permissionlib.onRequestPermissionsResult
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import com.example.avtovokzal.R as T


class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by sharedViewModel()

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
        calendarBtn.setOnClickListener {
            selectTime(
                ontimeSelected = { year: Int, month: Int, day: Int, hour: Int, min: Int ->
                    galleryViewModel.onTimeSelected(year,month,day,hour,min)
                }
            )
        }

        galleryViewModel.dialog.observe(this,EventObserver{
             showSuccessDialog(it);
        })
        galleryViewModel.spinner.observe(this,Observer{state:Boolean->
            showProgressBar(state)
        })
        galleryViewModel.advertModel.date.observe(this, Observer {
            showSelectedDate(it)
        })
        galleryViewModel.advertModel.address.observe(this, EventObserver {
            showAdress(it)
        })
        galleryViewModel.snackBar.observe(this,EventObserver{
            Snackbar.make(fromTV, it, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        })
        galleryViewModel.cities.observe(this, Observer {
            initAutoCompleteTextViewForCities( R.layout.select_dialog_singlechoice, it)
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

}
