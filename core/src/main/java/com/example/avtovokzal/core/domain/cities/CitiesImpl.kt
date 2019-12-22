package com.example.avtovokzal.core.domain

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await

class CitiesImpl : Cities {
    override suspend fun getCities(): Result<List<City>> {
        val db = FirebaseFirestore.getInstance()
        var result: Result<List<City>> = Result.Loading
        val source = Source.DEFAULT
        db.collection("adverts").document("cities")
            .get(source).addOnSuccessListener { documentSnapshot ->
            val cityList = arrayListOf<City>()
            @Suppress("UNCHECKED_CAST")
            val rawCityList = documentSnapshot["cityList"] as List<HashMap<*, *>>
            rawCityList.forEach {
                cityList.add(City(it["name"] as String?))
            }
            result = Result.Success(cityList)
        }
            .addOnFailureListener { e ->
                result = Result.Error(e)
                Log.w("Nurs", "Error adding document", e)
            }
            .await()
        return result
    }

    override fun saveNewCity(city: City) {
        val db = FirebaseFirestore.getInstance()
        db.collection("adverts").document("cities")
            .update("cityList", FieldValue.arrayUnion(city))
    }
}