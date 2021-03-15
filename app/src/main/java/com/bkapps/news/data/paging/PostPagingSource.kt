package com.bkapps.news.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bkapps.news.data.repository.PostRepository
import com.bkapps.news.model.Post
import retrofit2.HttpException
import java.io.IOException


const val POST_STARTING_PAGE_INDEX = 1

class PostPagingSource(private val repo: PostRepository) : PagingSource<Int, Post>() {

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val position = params.key ?: POST_STARTING_PAGE_INDEX
            val response = repo.fetchPosts(page = position)
            val prevKey = if (position == POST_STARTING_PAGE_INDEX) null else position - 1
            val nextKey = if (response.isEmpty()) null else position + 1
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}