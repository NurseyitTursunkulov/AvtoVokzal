package com.example.avtovokzal.core.domain.postAnAdd

import com.example.avtovokzal.core.domain.AdvertModel
import com.example.avtovokzal.core.domain.Result

interface SendingAdvert {
    suspend fun sendAdvert(advertModel: AdvertModel) : Result<Unit>
}