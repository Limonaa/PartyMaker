package com.elephantstudio.partymaker.viewmodels

import androidx.lifecycle.ViewModel
import com.elephantstudio.partymaker.data.Party
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NewPartyViewModel: ViewModel() {

    private val _partyList = MutableStateFlow<MutableList<Party>>(mutableListOf())
    val partyList: StateFlow<MutableList<Party>> = _partyList
    fun addParty(party: Party) {
        _partyList.value.add(party)
    }
}