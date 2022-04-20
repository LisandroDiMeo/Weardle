package com.example.android.wearable.wearwordle.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.android.wearable.wearwordle.presentation.viewmodels.CompleteWordViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val completeWordViewModel by viewModels<CompleteWordViewModel>()
        setContent {
            WearWordleApp(completeWordViewModel) { finish() }
        }
    }
}
