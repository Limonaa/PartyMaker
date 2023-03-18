package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.auth.AuthResult

interface AuthRepository {

    suspend fun signUp(username: String, password: String): AuthResult<Unit>

    suspend fun signIn(username: String, password: String): AuthResult<Unit>

    suspend fun authenticate(): AuthResult<Unit>
}