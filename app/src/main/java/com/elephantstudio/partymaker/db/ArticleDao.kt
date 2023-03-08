package com.elephantstudio.partymaker.db

import androidx.room.*
import com.elephantstudio.partymaker.data.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM article WHERE id = :id")
    suspend fun getArticleById(id: Int): Article?

    @Query("SELECT * FROM article")
    fun getArticles(): Flow<List<Article>>
}