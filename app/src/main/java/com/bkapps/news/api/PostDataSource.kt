package com.bkapps.news.api

import com.bkapps.news.model.Post
import com.bkapps.news.utils.NetworkResult

class PostDataSource(private val service: PostService) : NetworkCaller() {

    suspend fun selectPosts(page: Int): NetworkResult<List<Post>> {
        return getResult { service.getPosts(page) }
    }
}