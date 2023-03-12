package com.elephantstudio.partymaker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.data.Resource
import com.elephantstudio.partymaker.repo.AuthRepository
import com.elephantstudio.partymaker.repo.PartyRepository
import com.google.firebase.auth.FirebaseUser
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

    val parties = partyRepository.getAllParties()

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

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    val currentUser: FirebaseUser?
        get() = authRepository.currentUser

    init {
        if (authRepository.currentUser != null) {
            _loginFlow.value = Resource.Success(authRepository.currentUser!!)
        }
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = authRepository.login(email, password)
        _loginFlow.value = result
    }

    fun signupUser(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = authRepository.signup(name, email, password)
        _signupFlow.value = result
    }

    fun logout() {
        authRepository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }
}