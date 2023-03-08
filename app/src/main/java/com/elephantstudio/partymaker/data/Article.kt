package com.elephantstudio.partymaker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentSnapshot

@Entity
data class Article(
    @PrimaryKey (autoGenerate = true)
    var id: Int? = null,
    val articleName: String
)
