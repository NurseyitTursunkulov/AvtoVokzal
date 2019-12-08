package com.example.avtovokzal.core.domain

import com.example.avtovokzal.postAnAdd.Result

interface SendingAdvert {
    suspend fun sendAdvert(advertModel: AdvertModel) : Result<Unit>
}