package com.elephantstudio.partymaker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elephantstudio.partymaker.data.Party

@Database(
    entities = [Party::class],
    version = 1
)
abstract class PartyDatabase: RoomDatabase() {

    abstract val dao: PartyDao
}