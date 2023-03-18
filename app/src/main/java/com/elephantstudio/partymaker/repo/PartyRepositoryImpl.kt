package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.data.Resource
import com.elephantstudio.partymaker.data.User
import com.elephantstudio.partymaker.db.FirestoreDatabase
import com.elephantstudio.partymaker.db.PartyDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PartyRepositoryImpl @Inject constructor(
//    private val dao: PartyDao,
    private val db: FirestoreDatabase
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

    override suspend fun getUserInfo(): Resource<User> {
//        return dao.getAllParties()
        return db.getUserInfo()
    }

}