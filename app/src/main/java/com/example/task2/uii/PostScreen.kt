package com.example.task2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.task2.data.PostEntity

import com.example.task2.util.ApiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel: PostViewModel) {
    val response by viewModel.response.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Posts") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue, titleContentColor = Color.White)
            )
        }
    ) { innerPadding ->
        when (response) {
            is ApiState.Success -> {
                LazyColumn(
                    contentPadding = innerPadding,
                    modifier = Modifier.padding(8.dp)
                ) {
                    items((response as ApiState.Success).data) { post ->
                        EachRow(post = post)
                    }
                }
            }
            is ApiState.Failure -> {
                Text(
                    text = "Error: ${(response as ApiState.Failure).msg.message}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            ApiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            ApiState.Empty -> {
                Text(
                    text = "No data available",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun EachRow(post: PostEntity) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = post.body,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostScreen() {
    // Use a mock view model for preview
    // val mockViewModel = remember { PostViewModel(MockPostRepository()) }
    // PostScreen(viewModel = mockViewModel)
}
