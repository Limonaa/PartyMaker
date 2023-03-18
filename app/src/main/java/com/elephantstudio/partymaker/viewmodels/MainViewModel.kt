package com.elephantstudio.partymaker.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elephantstudio.partymaker.data.AuthState
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.repo.AuthRepository
import com.elephantstudio.partymaker.repo.PartyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val partyRepository: PartyRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    init {
        authenticate()
    }

    fun addParty(party: Party) {
        viewModelScope.launch {
            partyRepository.insertParty(party)
        }
    }

    private val _selectedParty = MutableStateFlow<Party?>(null)
    val selectedParty: StateFlow<Party?> = _selectedParty
    fun setSelectedParty(party: Party) {
        _selectedParty.value = party
    }

    fun signUp(username: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.signUp(
                username = username,
                password = password
            )
        }
    }

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.signIn(
                username = username,
                password = password
            )
        }
    }

    fun authenticate() {
        viewModelScope.launch {
            val result = authRepository.authenticate()
        }
    }



}