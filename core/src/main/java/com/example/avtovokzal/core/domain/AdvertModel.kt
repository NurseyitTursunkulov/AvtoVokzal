package com.example.avtovokzal.core.domain

import androidx.lifecycle.MutableLiveData
import java.util.*

data class AdvertModel(
    val price: String?,
    val location: MyLatLng?,
    val address: String?,
    val fromCity: String?,
    val toCity: String?,
    val date: Date?,
    val id:String = UUID.randomUUID().toString()
//    ,
//    val freeSeat: Int,
//    val description: String?
) {
    constructor() : this(
        null, null, null,
        null, null, null
//        , 0, null
    )
}

data class MyLatLng(val langtitute: Double?, val longtitute: Double?) {
    constructor() : this(null, null)
}

data class City(val name: String?, val location: MyLatLng? = null) {
    constructor() : this(null)
}