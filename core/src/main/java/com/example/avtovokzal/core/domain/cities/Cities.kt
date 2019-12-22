package com.example.avtovokzal.core.domain

interface Cities {
    suspend fun getCities(): Result<List<City>>
    fun saveNewCity(city: City)
}