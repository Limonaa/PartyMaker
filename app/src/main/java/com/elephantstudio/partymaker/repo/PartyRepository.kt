package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.data.Party
import kotlinx.coroutines.flow.Flow

interface PartyRepository {

    suspend fun insertParty(party: Party)

    suspend fun deleteParty(party: Party)

    suspend fun getPartyById(id: Int): Party?

    fun getAllParties(): Flow<List<Party>>
}