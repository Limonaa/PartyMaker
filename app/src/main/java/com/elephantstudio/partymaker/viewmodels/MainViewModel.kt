package com.elephantstudio.partymaker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.repo.PartyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PartyRepository
) : ViewModel() {

    val parties = repository.getAllParties()

    fun addParty(party: Party) {
        viewModelScope.launch {
            repository.insertParty(party)
        }
    }
}