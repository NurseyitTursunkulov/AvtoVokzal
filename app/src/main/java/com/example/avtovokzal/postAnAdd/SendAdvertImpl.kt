package com.example.avtovokzal.postAnAdd

import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import android.util.Log
import com.example.avtovokzal.ui.gallery.AdvertModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SendAdvertImpl() : SendingAdert {

    override suspend fun sendAdvert(advertModel: AdvertModel): Result<Unit> {
        val db = FirebaseFirestore.getInstance()
        var result : Result<Unit> = Result.Loading

            db.collection("users")
                .add(advertModel)
                .addOnSuccessListener { documentReference ->
                    result = Result.Success(Unit)
                    Log.d("Nurs", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    result = Result.Error(e)
                    Log.w("Nurs", "Error adding document", e)
                }
                .await()
            Log.d("Nurs","after $result")
        return result
    }
}
