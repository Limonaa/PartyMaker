package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.db.PartyDao
import kotlinx.coroutines.flow.Flow

class PartyRepositoryImpl(
    private val dao: PartyDao
): PartyRepository {

    //ROOM DATABASE
    override suspend fun insertParty(party: Party) {
        dao.insertParty(party)
    }

    override suspend fun deleteParty(party: Party) {
        dao.deleteParty(party)
    }

    override suspend fun getPartyById(id: Int): Party? {
        return dao.getPartyById(id)
    }

    override fun getAllParties(): Flow<List<Party>> {
        return dao.getAllParties()
    }

    //FIRESTORE DATABASE
    override suspend fun addPartyToFirestore(party: Party) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePartyFromFirestore(party: Party) {
        TODO("Not yet implemented")
    }
}