package com.example.avtovokzal.domain

import com.example.avtovokzal.postAnAdd.Result
import com.example.avtovokzal.ui.gallery.AdvertModel

interface SendingAdert {
    suspend fun sendAdvert(advertModel: AdvertModel) : Result<Unit>
}