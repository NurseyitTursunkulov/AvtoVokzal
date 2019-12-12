package com.example.avtovokzal.ui.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avtovokzal.core.domain.AdvertModel
import com.example.avtovokzal.core.domain.postAnAdd.Result
import com.example.avtovokzal.core.domain.SendingAdvert
import com.example.avtovokzal.util.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class GalleryViewModel(val sendAdvert: SendingAdvert) : ViewModel() {
    private val _snackBar = MutableLiveData<String>()
    private val _dialog = MutableLiveData<Event<String>>()
    val dialog = _dialog
    private val _spinner = MutableLiveData<Boolean>()
    val spinner =_spinner
    val advertModel = AdvertModelPresenterLevel()
    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    fun onTimeSelected(year: Int, month: Int, day: Int, hour: Int, min: Int) {
        val sfd = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        sfd.format(Date(year, month, day, hour, min))
        Log.d("Nurs", sfd.format(Date(year, month, day, hour, min)))
        advertModel.date.postValue(Date(year, month, day, hour, min))
    }

    fun publicateAdd() {
        launchDataLoad {
            sendAdvert.sendAdvert(advertModel.convertToDomainModel()).let { result: Result<Unit> ->
                if (result is Result.Success) {
                    _dialog.postValue(Event("ваше обьявление успешно опубликовано"))
                } else {
                    _dialog.postValue(Event("произошла ошибка"))
                }
            }
        }
    }

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