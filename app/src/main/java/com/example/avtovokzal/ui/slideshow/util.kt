package com.example.avtovokzal.ui.slideshow

import android.annotation.SuppressLint
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_slideshow.*
import java.text.SimpleDateFormat
import java.util.*

fun SlideshowFragment.initAutoCompleteTextViewForCities(layoutId: Int, citiesInKG: List<String>) {
    val adapter: ArrayAdapter<String> =
        ArrayAdapter<String>(requireContext(), layoutId, citiesInKG)
    fromTV.threshold = 1;
    fromTV.setAdapter(adapter)
    toTV.threshold = 1
    toTV.setAdapter(adapter)
}

fun SlideshowFragment.showSuccessDialog(it: String) {
    MaterialAlertDialogBuilder(context)
        .setTitle(it)
        .setPositiveButton("Ok", null)
        .show()
}


fun SlideshowFragment.showProgressBar(state: Boolean) {
    if (state) {
        progressBar2.visibility = View.VISIBLE
        fromTV.isEnabled = false
//        location_button.isEnabled = false
        toTV.isEnabled = false
//        driving_licience_btn.isEnabled = false
        calendarBtn.isEnabled = false
        publicate_button.isEnabled = false
    } else {
        progressBar2.visibility = View.INVISIBLE
        fromTV.isEnabled = true
//        location_button.isEnabled = true
        toTV.isEnabled = true
//        driving_licience_btn.isEnabled = true
        calendarBtn.isEnabled = true
        publicate_button.isEnabled = true
    }
}

@SuppressLint("SimpleDateFormat")
fun SlideshowFragment.showSelectedDate(it: Date?) {
    dateTV.visibility = View.VISIBLE
    dateTV.text = SimpleDateFormat("dd MMM yy HH:mm ").format(it)
}

fun SlideshowFragment.showAdress(it: String) {
//    adressTV.visibility = View.VISIBLE
//    adressTV.text = it
}
