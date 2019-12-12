package com.example.avtovokzal.core.domain

import androidx.lifecycle.MutableLiveData
import java.util.*

data class AdvertModel(
    val price: MutableLiveData<String> = MutableLiveData(),
    val location: MutableLiveData<MyLatLng> = MutableLiveData(),
    val fromCity: MutableLiveData<String> = MutableLiveData(),
    val toCity: MutableLiveData<String> = MutableLiveData(),
    val date: MutableLiveData<Date> = MutableLiveData()
)

data class MyLatLng(val longtitute: Double, val langtitute: Double)