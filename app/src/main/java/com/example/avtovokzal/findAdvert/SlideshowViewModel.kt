package com.example.avtovokzal.findAdvert

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avtovokzal.core.domain.AdvertModel
import com.example.avtovokzal.core.domain.Cities
import com.example.avtovokzal.core.domain.Result
import com.example.avtovokzal.core.domain.findAdd.FindingAdverts
import com.example.avtovokzal.core.domain.postAnAdd.SendingAdvert
import com.example.avtovokzal.postAdvert.AdvertModelPresenterLevel
import com.example.avtovokzal.util.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class SlideshowViewModel(
    val sendAdvert: SendingAdvert,
    val gettingCities: Cities,
    val findingAdverts: FindingAdverts
) : ViewModel() {
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
    private val _advertsLoadedEvent = MutableLiveData<Event<List<AdvertModel>>>()
    val advertsLoadedEvent = _advertsLoadedEvent

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

    fun findAdd() {
//        _advertsLoadedEvent.postValue(Event(listOf(AdvertModel())))
        if (validateInputs())
            launchDataLoad {
                with(advertModel) {
                    fromCity.value?.let { fromCityV ->
                        toCity.value?.let { toCityV ->
                            date.value?.let { dateV ->
                                val res = findingAdverts.findAdd(fromCityV, toCityV, dateV)
                                if (res is Result.Success) {
                                    Log.d("Nurs","result = ${res.data}")
                                    res.data.forEach {
                                        Log.d("Nurs","result each = ${it}")
                                    }
                                    _advertsLoadedEvent.postValue(Event(res.data))
                                } else {
                                    _dialog.postValue(Event("произошла ошибка"))
                                }
                            }
                        }
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
            } else return true
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