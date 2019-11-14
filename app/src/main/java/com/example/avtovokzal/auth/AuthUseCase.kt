package com.example.avtovokzal.auth

import androidx.lifecycle.LiveData

interface AuthUseCase {
    val authorizationState : LiveData<Boolean>
    fun signIn()
    fun signOut()
}