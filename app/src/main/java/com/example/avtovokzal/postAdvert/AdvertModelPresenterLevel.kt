package com.example.avtovokzal.postAdvert

import androidx.lifecycle.MutableLiveData
import com.example.avtovokzal.core.domain.AdvertModel
import com.example.avtovokzal.core.domain.MyLatLng
import com.example.avtovokzal.util.Event
import com.google.android.gms.maps.model.LatLng
import java.util.*

data class AdvertModelPresenterLevel(
    val price: MutableLiveData<String> = MutableLiveData(),
    val location: MutableLiveData<LatLng> = MutableLiveData(),
    val address: MutableLiveData<Event<String>> = MutableLiveData(),
    val fromCity: MutableLiveData<String> = MutableLiveData(),
    val toCity: MutableLiveData<String> = MutableLiveData(),
    val date: MutableLiveData<Date> = MutableLiveData(),
    var id: String = UUID.randomUUID().toString()
) {
    fun convertToDomainModel(): AdvertModel {
        return AdvertModel(
            price = price.value,
            location = MyLatLng(location.value?.latitude, location.value?.longitude),
            address = address.value?.peekContent(),
            fromCity = fromCity.value,
            toCity = toCity.value,
            date = date.value
        )
    }
}