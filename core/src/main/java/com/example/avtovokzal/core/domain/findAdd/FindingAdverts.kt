package com.example.avtovokzal.core.domain.findAdd

import com.example.avtovokzal.core.domain.AdvertModel
import com.example.avtovokzal.core.domain.Result
import java.util.Date

interface FindingAdverts {
    suspend fun findAdd(fromCity:String,toCity:String,date: Date):Result<List<AdvertModel>>
}