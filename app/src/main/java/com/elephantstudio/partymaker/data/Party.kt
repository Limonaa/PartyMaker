package com.elephantstudio.partymaker.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Party(
    @PrimaryKey (autoGenerate = true)
    var id: Int? = null,
    val partyName: String,
    val partyDate: String,
    val partyDescription: String,
    val partyPlaylistLink: String,
    val isArrivalConfirmed: Boolean,

)
