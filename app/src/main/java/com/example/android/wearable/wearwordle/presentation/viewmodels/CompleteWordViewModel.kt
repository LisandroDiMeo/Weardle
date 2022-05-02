package com.example.android.wearable.wearwordle.presentation.viewmodels

import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.wearable.wearwordle.database.entities.Word
import com.example.android.wearable.wearwordle.gamelogic.WordGuess
import com.example.android.wearable.wearwordle.network.repository.WordsRepository
import kotlinx.coroutines.flow.Flow

class CompleteWordViewModel : ViewModel() {

    private lateinit var wordGuess: WordGuess

    lateinit var repository: WordsRepository

    private val _wordGuesses = MutableLiveData<List<AnnotatedString>>(listOf())
    val wordGuesses : LiveData<List<AnnotatedString>> get() = _wordGuesses

    private val _gameStatus = MutableLiveData(GameStatus.PLAYING)
    val gameStatus : LiveData<GameStatus> get() = _gameStatus

    private val onGameOver : () -> Unit = {
        _gameStatus.postValue(GameStatus.LOST)
    }
    private val onGameWon : () -> Unit = {
        _gameStatus.postValue(GameStatus.WON)
    }

    fun fetchWordFromLanguage(language: Language): Flow<Word> = repository.fetchRandomWord(language)

    fun startGuessingWithWord(word: String){
        wordGuess = WordGuess(word.uppercase(), onGuessCorrect = onGameWon, onGameOver = onGameOver)
    }

    fun doGuessOfWord(guess: String){
        _wordGuesses.value = _wordGuesses.value!!.plus(wordGuess.guessWord(guess))
    }
}

enum class Language {
    EN, SP, FR
}

fun Language.tableName() = "words_${this.name.lowercase()}"

enum class GameStatus {
    PLAYING, LOST, WON
}
