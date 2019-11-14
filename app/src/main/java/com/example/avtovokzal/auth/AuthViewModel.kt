package com.example.avtovokzal.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        if (auth.currentUser == null) {

        }
    }
}
