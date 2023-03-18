package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.data.Party

interface PartyRepository {

    suspend fun insertParty(party: Party)

    suspend fun deleteParty(party: Party)

    suspend fun getPartyById(id: Int): Party?

}