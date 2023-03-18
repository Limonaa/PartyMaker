package com.elephantstudio.partymaker.data

import com.elephantstudio.partymaker.viewmodels.MainViewModel

data class User(
    val name: String = "",
    val email: String = "",
    val partyList: List<Party> = listOf()
)
