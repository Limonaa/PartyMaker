package com.elephantstudio.partymaker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.elephantstudio.partymaker.auth.AuthApi
import com.elephantstudio.partymaker.db.PartyDatabase
import com.elephantstudio.partymaker.repo.AuthRepository
import com.elephantstudio.partymaker.repo.AuthRepositoryImpl
import com.elephantstudio.partymaker.repo.PartyRepository
import com.elephantstudio.partymaker.repo.PartyRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
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
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.100.109:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSharedPrefs(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api, prefs)
    }
}