package com.elephantstudio.partymaker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elephantstudio.partymaker.data.Article

@Database(
    entities = [Article::class],
    version = 1
)

abstract class ArticleDatabase: RoomDatabase() {

    abstract val dao: ArticleDao
}