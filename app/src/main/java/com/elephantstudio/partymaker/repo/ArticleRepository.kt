package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.data.Article
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface ArticleRepository {

    suspend fun insertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    suspend fun getArticleById(id: Int): Article?

    fun getArticles(): Flow<List<Article>>
    }
