    package com.example.task2.util


    import com.example.task2.data.PostEntity


    sealed class ApiState {
        class Success(val data: List<PostEntity>) :ApiState()
        class Failure(val msg:Throwable) :ApiState()
        object Loading :ApiState()
        object Empty :ApiState()
    }