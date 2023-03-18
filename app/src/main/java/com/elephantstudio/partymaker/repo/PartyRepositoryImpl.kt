package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.db.PartyDao
import javax.inject.Inject

class PartyRepositoryImpl @Inject constructor(
    private val dao: PartyDao,
): PartyRepository {

    //ROOM DATABASE
    override suspend fun insertParty(party: Party) {
//        dao.insertParty(party)
    }

    override suspend fun deleteParty(party: Party) {
//        dao.deleteParty(party)
    }

    override suspend fun getPartyById(id: Int): Party? {
//        return dao.getPartyById(id)
        return null
    }


}