package com.elephantstudio.partymaker.di

import android.app.Application
import androidx.room.Room
import com.elephantstudio.partymaker.db.PartyDao
import com.elephantstudio.partymaker.db.PartyDatabase
import com.elephantstudio.partymaker.repo.PartyRepository
import com.elephantstudio.partymaker.repo.PartyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePartyDatabase(app: Application): PartyDatabase {

        return Room.databaseBuilder(
            app,
            PartyDatabase::class.java,
            "party_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePartyRepository(db: PartyDatabase): PartyRepository {

        return PartyRepositoryImpl(db.dao)
    }
}