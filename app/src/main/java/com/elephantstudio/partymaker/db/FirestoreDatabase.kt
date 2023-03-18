package com.elephantstudio.partymaker.db

import com.elephantstudio.partymaker.data.Resource
import com.elephantstudio.partymaker.data.User
import com.elephantstudio.partymaker.repo.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException

class FirestoreDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun getUserInfo(): Resource<User> {
        auth.currentUser?.uid?.let { userId ->
            try {
                val query = firestore.collection("Users").document(userId).get().await()
                return Resource.Success(query.toObject(User::class.java)!!)
            } catch (e: FirebaseFirestoreException) {
                return Resource.Failure(e)
            }
        }?: kotlin.run {
            //TODO do zmiany to jest
            return Resource.Failure(Exception("User not found"))
        }

    }
}