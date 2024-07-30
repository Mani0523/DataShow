package com.example.task2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)