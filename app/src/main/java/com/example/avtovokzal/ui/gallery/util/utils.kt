package com.example.avtovokzal.ui.gallery.util

import android.app.TimePickerDialog
import android.widget.TimePicker
import com.example.avtovokzal.ui.gallery.GalleryFragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

fun GalleryFragment.selectTime() {
    val builder = MaterialDatePicker.Builder.datePicker();
    val c = Calendar.getInstance();
    val mHour = c.get(Calendar.HOUR_OF_DAY);
    val mMinute = c.get(Calendar.MINUTE);

    // Launch Time Picker Dialog
    val timePickerDialog = TimePickerDialog(requireContext(),
        object : TimePicker.OnTimeChangedListener,
            TimePickerDialog.OnTimeSetListener {
            override fun onTimeChanged(
                view: TimePicker?,
                hourOfDay: Int,
                minute: Int
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                galleryViewModel.time.postValue(TimeModel(hourOfDay, minute))
            }

        }, mHour, mMinute, true
    );
    timePickerDialog.show();
    val picker = builder.build();
    picker.show(requireFragmentManager(), picker.toString())
}