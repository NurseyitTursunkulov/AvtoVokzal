package com.example.avtovokzal.postAnAdd

import kotlinx.coroutines.tasks.await
import android.util.Log
import com.example.avtovokzal.core.domain.SendingAdvert
//import com.example.avtovokzal.ui.gallery.AdvertModel
import com.google.firebase.firestore.FirebaseFirestore

class SendAdvertImpl() : SendingAdvert {

//    override suspend fun sendAdvert(advertModel: AdvertModel): Result<Unit> {
//        val db = FirebaseFirestore.getInstance()
//        var result : Result<Unit> = Result.Loading
//
//            db.collection("users")
//                .add(advertModel)
//                .addOnSuccessListener { documentReference ->
//                    result = Result.Success(Unit)
//                    Log.d("Nurs", "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    result = Result.Error(e)
//                    Log.w("Nurs", "Error adding document", e)
//                }
//                .await()
//            Log.d("Nurs","after $result")
//        return result
//    }

    override suspend fun sendAdvert(advertModel: com.example.avtovokzal.core.domain.AdvertModel): Result<Unit> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
