package com.example.avtovokzal.ui.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avtovokzal.ui.gallery.util.DateModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class GalleryViewModel : ViewModel() {
    val date = MutableLiveData<Date>()
    fun onTimeSelected(year: Int, month: Int, day: Int, hour: Int, min: Int) {
        val sfd = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        sfd.format(Date( year,month,day,hour,min))
        date.postValue(Date( year,month,day,hour,min))
    }

    fun publicateAdd(){
        val db = FirebaseFirestore.getInstance()

// Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
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