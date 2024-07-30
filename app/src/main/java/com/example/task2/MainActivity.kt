package com.example.task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task2.ui.PostScreen
import com.example.task2.ui.PostViewModel
import com.example.task2.ui.theme.Task2Theme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel: PostViewModel = viewModel()
                    PostScreen(viewModel = viewModel)

                }
            }
        }
    }
}

