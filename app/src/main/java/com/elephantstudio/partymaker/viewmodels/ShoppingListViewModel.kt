package com.elephantstudio.partymaker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elephantstudio.partymaker.data.Article
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.repo.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ArticleRepository
): ViewModel() {

    val articles = repository.getArticles()

    fun addArticle(article: Article) {
        viewModelScope.launch {
            repository.insertArticle(article)
        }
    }
}