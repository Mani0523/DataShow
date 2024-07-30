package com.example.task2.ui


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.task2.data.PostRepository
import com.example.task2.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor( private val postRepository: PostRepository) : ViewModel() {

    private val _response = MutableStateFlow<ApiState>(ApiState.Empty)
    val response: StateFlow<ApiState> = _response.asStateFlow()

    init {
        fetchAndSavePosts()
    }

    private fun fetchAndSavePosts() = viewModelScope.launch {
        try {
            _response.value = ApiState.Loading
            // Fetch posts from API
            val posts = postRepository.getPost()
            // Save posts to database
            postRepository.savePosts(posts)
            loadPostsFromDatabase()
        } catch (exception: Exception) {
            _response.value = ApiState.Failure(exception)
        }
    }

    private fun loadPostsFromDatabase() = viewModelScope.launch {
        postRepository.getPostsFromDatabase().collect { posts ->
            _response.value = if (posts.isEmpty()) {
                ApiState.Empty
            } else {
                ApiState.Success(posts)
            }
        }
    }
}
