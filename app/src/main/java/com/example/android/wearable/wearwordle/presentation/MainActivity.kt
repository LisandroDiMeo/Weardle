package com.example.android.wearable.wearwordle.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.room.Room
import com.example.android.wearable.wearwordle.database.AppDatabase
import com.example.android.wearable.wearwordle.network.repository.WordsRepository
import com.example.android.wearable.wearwordle.presentation.viewmodels.CompleteWordViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.database(applicationContext)
        val completeWordViewModel by viewModels<CompleteWordViewModel>()
        completeWordViewModel.repository = WordsRepository(db.wordsDao())
        setContent {
            WearWordleApp(completeWordViewModel) { finish() }
        }
    }
}
