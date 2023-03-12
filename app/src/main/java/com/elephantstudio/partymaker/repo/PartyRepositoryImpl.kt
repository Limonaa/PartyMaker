package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.data.Resource
import com.elephantstudio.partymaker.db.PartyDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PartyRepositoryImpl @Inject constructor(
    private val dao: PartyDao,
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

}