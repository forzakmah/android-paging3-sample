package com.bkapps.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bkapps.news.data.repository.PostRepository
import com.bkapps.news.data.paging.PostPagingSource
import com.bkapps.news.model.Post
import kotlinx.coroutines.flow.Flow

class PostsViewModel(private val repository: PostRepository) : ViewModel() {

    val posts: Flow<PagingData<Post>> = selectPosts()

    private fun selectPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PostPagingSource(repository) }
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}

