package com.elephantstudio.partymaker.db

import androidx.room.*
import com.elephantstudio.partymaker.data.Party
import kotlinx.coroutines.flow.Flow

@Dao
interface PartyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParty(party: Party)

    @Delete
    suspend fun deleteParty(party: Party)

    @Query("SELECT * FROM party WHERE id = :id")
    fun getPartyById(id: Int): Party?

    @Query("SELECT * FROM party")
    fun getAllParties(): Flow<List<Party>>
}