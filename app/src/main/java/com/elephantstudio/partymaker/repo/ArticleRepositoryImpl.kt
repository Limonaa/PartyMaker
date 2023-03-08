package com.elephantstudio.partymaker.repo

import com.elephantstudio.partymaker.data.Article
import com.elephantstudio.partymaker.db.ArticleDao
import kotlinx.coroutines.flow.Flow

class ArticleRepositoryImpl(
    private val dao: ArticleDao
): ArticleRepository {
    override suspend fun insertArticle(article: Article) {
        dao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(article)
    }

    override suspend fun getArticleById(id: Int): Article? {
        return dao.getArticleById(id)
    }

    override fun getArticles(): Flow<List<Article>> {
        return dao.getArticles()
    }
}