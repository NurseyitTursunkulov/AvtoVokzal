package com.example.avtovokzal.ui.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avtovokzal.postAnAdd.Result
import com.example.avtovokzal.domain.SendingAdert
import com.example.avtovokzal.ui.gallery.util.DateModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class GalleryViewModel(val sendAdvert: SendingAdert) : ViewModel() {
    private val _snackBar = MutableLiveData<String>()
    private val _spinner = MutableLiveData<Boolean>()
    val spinner = MutableLiveData<Boolean>()
    val advertModel = AdvertModel()

    fun onTimeSelected(year: Int, month: Int, day: Int, hour: Int, min: Int) {
        val sfd = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        sfd.format(Date(year, month, day, hour, min))
        Log.d("Nurs", sfd.format(Date(year, month, day, hour, min)))
        advertModel.date.postValue(Date(year, month, day, hour, min))
    }

    fun publicateAdd() {
        launchDataLoad {
            sendAdvert.sendAdvert(advertModel).let { result: Result<Unit> ->
                if (result is Result.Success) {

                } else {

                }
            }
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
    val time = MutableLiveData<DateModel>()
    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: Error) {
                _snackBar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }
}