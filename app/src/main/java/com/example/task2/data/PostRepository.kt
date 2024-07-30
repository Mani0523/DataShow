package com.example.task2.data


import com.example.task2.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) {

    suspend fun getPost(): List<PostEntity> {
        return apiService.getPost()
    }

    suspend fun savePosts(posts: List<PostEntity>) {
        postDao.insertAll(posts)
    }

    fun getPostsFromDatabase(): Flow<List<PostEntity>> {
        return postDao.getAllPosts()
    }
}
