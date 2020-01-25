package com.example.avtovokzal.core.domain.findAdd

import android.util.Log
import com.example.avtovokzal.core.domain.AdvertModel
import com.example.avtovokzal.core.domain.City
import com.example.avtovokzal.core.domain.Result
import com.example.avtovokzal.core.domain.succeeded
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import java.util.*

class FindingAdvertsImpl : FindingAdverts {
    override suspend fun findAdd(
        fromCity: String,
        toCity: String,
        date: Date
    ): Result<List<AdvertModel>> {
        val db = FirebaseFirestore.getInstance()
        val list = ArrayList<AdvertModel>()
        var result: Result<ArrayList<AdvertModel>> = Result.Loading
        db.collection("adverts").document("$fromCity-$toCity")
            .get().addOnSuccessListener { documentSnapshot ->
                val advertModel = documentSnapshot.toObject(AdvertModel::class.java)
                if (advertModel != null)
                    list.add(advertModel as AdvertModel)
//                result = Result.Success(arrayListOf(advertModel) as ArrayList<AdvertModel>)
            }
            .addOnFailureListener { e ->
                result = Result.Error(e)
            }
            .await()
        db.collection("adverts").document("$fromCity-$toCity")
            .collection("companion").get().addOnSuccessListener { documentSnapshot ->
                documentSnapshot.documents.forEach {
                    val advertModel = it.toObject(AdvertModel::class.java)
                    if (advertModel != null)
                        list.add(advertModel as AdvertModel)
//                    result = Result.Success(listOf(advertModel) as List<AdvertModel>)
                }

            }
            .addOnFailureListener { e ->
                result = Result.Error(e)
            }
            .await()
        if (list.isNotEmpty()) result = Result.Success(list)
        return result
    }
}