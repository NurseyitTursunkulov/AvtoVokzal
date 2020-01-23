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


class PostAdvertFragment : Fragment() {

    private val postAdvertViewModel: PostAdvertViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflate<FragmentGalleryBinding>(inflater, T.layout.fragment_gallery, container, false)
        binding.viewModel = postAdvertViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarBtn.setOnClickListener {
            selectTime(
                ontimeSelected = { year: Int, month: Int, day: Int, hour: Int, min: Int ->
                    postAdvertViewModel.onTimeSelected(year,month,day,hour,min)
                }
            )
        }

        postAdvertViewModel.dialog.observe(viewLifecycleOwner,EventObserver{
             showSuccessDialog(it);
        })
        postAdvertViewModel.spinner.observe(viewLifecycleOwner,Observer{ state:Boolean->
            showProgressBar(state)
        })
        postAdvertViewModel.advertModel.date.observe(viewLifecycleOwner, Observer {
            showSelectedDate(it)
        })
        postAdvertViewModel.advertModel.address.observe(viewLifecycleOwner, EventObserver {
            showAdress(it)
        })
        postAdvertViewModel.snackBar.observe(viewLifecycleOwner,EventObserver{
            Snackbar.make(fromTV, it, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        })
        postAdvertViewModel.cities.observe(viewLifecycleOwner, Observer {
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
