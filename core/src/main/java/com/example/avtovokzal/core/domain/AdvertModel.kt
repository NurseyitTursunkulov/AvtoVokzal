package com.example.avtovokzal.core.domain

import androidx.lifecycle.MutableLiveData
import java.util.*

data class AdvertModel(
    val price: String?,
    val location:MyLatLng?,
    val address:String?,
    val fromCity:String?,
    val toCity: String?,
    val date: Date?
)

data class MyLatLng( val langtitute: Double?,val longtitute: Double?)