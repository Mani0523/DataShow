package com.example.task2.network

import com.example.task2.data.PostEntity

import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    suspend fun getPost(): List<PostEntity>
}
