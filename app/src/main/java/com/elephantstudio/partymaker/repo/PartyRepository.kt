package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.data.Party
import kotlinx.coroutines.flow.Flow

interface PartyRepository {

    //ROOM DATABASE
    suspend fun insertParty(party: Party)

    suspend fun deleteParty(party: Party)

    suspend fun getPartyById(id: Int): Party?

    fun getAllParties(): Flow<List<Party>>

    //FIRESTORE DATABASE
    suspend fun addPartyToFirestore(party: Party)

    suspend fun deletePartyFromFirestore(party: Party)
}