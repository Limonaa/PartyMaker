package com.elephantstudio.partymaker.di

import android.app.Application
import androidx.room.Room
import com.elephantstudio.partymaker.db.PartyDao
import com.elephantstudio.partymaker.db.PartyDatabase
import com.elephantstudio.partymaker.repo.AuthRepository
import com.elephantstudio.partymaker.repo.AuthRepositoryImpl
import com.elephantstudio.partymaker.repo.PartyRepository
import com.elephantstudio.partymaker.repo.PartyRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
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

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}