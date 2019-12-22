package com.example.avtovokzal.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avtovokzal.core.domain.Cities
import com.example.avtovokzal.core.domain.postAnAdd.SendingAdvert
import com.example.avtovokzal.core.domain.Result
import com.example.avtovokzal.util.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class GalleryViewModel(val sendAdvert: SendingAdvert, val gettingCities : Cities) : ViewModel() {
    private val _snackBar = MutableLiveData<Event<String>>()
    val snackBar = _snackBar
    private val _dialog = MutableLiveData<Event<String>>()
    val dialog = _dialog
    private val _spinner = MutableLiveData<Boolean>()
    val spinner = _spinner
    val advertModel = AdvertModelPresenterLevel()
    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
    val cities = MutableLiveData<List<String>>()

    init {
        getCities()
    }

    private fun getCities() {
        launchDataLoad {
            when (val citiList = gettingCities.getCities()) {
                is Result.Success -> {
                    val h = citiList.data.mapNotNull {
                        it.name
                    }
                    cities.postValue(h)
                }
            }
        }
    }

    fun onTimeSelected(year: Int, month: Int, day: Int, hour: Int, min: Int) {
        advertModel.date.postValue(Date(year, month, day, hour, min))
    }

    fun publicateAdd() {
        if (validateInputs())
            launchDataLoad {
                sendAdvert.sendAdvert(advertModel.convertToDomainModel())
                    .let { result: Result<Unit> ->
                        if (result is Result.Success) {
                            _dialog.postValue(Event("ваше обьявление успешно опубликовано"))
                        } else {
                            _dialog.postValue(Event("произошла ошибка"))
                        }
                    }
            }
    }

    private fun validateInputs(): Boolean {
        with(advertModel) {
            if (fromCity.value == null || toCity.value == null) {
                _dialog.postValue(Event("выберите город"))
                return false
            } else if (date.value == null) {
                _dialog.postValue(Event("выберите дату"))
                return false
            }
            else return true
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: Error) {
                _snackBar.value = Event(error.toString())
            } finally {
                _spinner.value = false
            }
        }
    }
}