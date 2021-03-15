package com.bkapps.news.data.repository

import com.bkapps.news.api.PostDataSource
import com.bkapps.news.model.Post
import com.bkapps.news.utils.NetworkResult


class PostRepository(private val remote: PostDataSource) {

    suspend fun fetchPosts(page: Int): List<Post> {
        return when (val response = remote.selectPosts(page)) {
            is NetworkResult.Success -> response.data
            is NetworkResult.Error -> throw response.exception
        }
    }
}