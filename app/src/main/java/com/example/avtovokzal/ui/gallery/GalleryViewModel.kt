package com.example.avtovokzal.ui.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avtovokzal.ui.gallery.util.DateModel
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class GalleryViewModel : ViewModel() {
    val advertModel = AdvertModel()
    fun onTimeSelected(year: Int, month: Int, day: Int, hour: Int, min: Int) {
        val sfd = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        sfd.format(Date(year, month, day, hour, min))
        Log.d("Nurs", sfd.format(Date(year, month, day, hour, min)))
        advertModel.date.postValue(Date(year, month, day, hour, min))
    }

    fun publicateAdd() {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .add(advertModel)
            .addOnSuccessListener { documentReference ->

                Log.d("Nurs", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Nurs", "Error adding document", e)
            }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
    val time = MutableLiveData<DateModel>()

}