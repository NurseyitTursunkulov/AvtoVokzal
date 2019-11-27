package com.example.avtovokzal.postAnAdd

import com.example.avtovokzal.ui.gallery.AdvertModel

interface SendingAdert {
    suspend fun sendAdvert(advertModel: AdvertModel) : Result<Unit>
}