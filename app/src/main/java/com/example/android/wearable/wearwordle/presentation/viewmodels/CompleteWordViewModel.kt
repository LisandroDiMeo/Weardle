package com.example.android.wearable.wearwordle.presentation.viewmodels

import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.wearable.wearwordle.gamelogic.WordGuess
import kotlinx.coroutines.launch

class CompleteWordViewModel : ViewModel() {

    private lateinit var wordGuess: WordGuess

    private val _word = MutableLiveData<String>(null)
    val word : LiveData<String> get() = _word

    private val _wordGuesses = MutableLiveData<List<AnnotatedString>>(listOf())
    val wordGuesses : LiveData<List<AnnotatedString>> get() = _wordGuesses

    private val _gameWon = MutableLiveData<Boolean>(null)
    val gameWon : LiveData<Boolean> get() = _gameWon

    fun fetchWordFromLanguage(language: Language){
        viewModelScope.launch {
            _word.value = WORDS.random()
            wordGuess = WordGuess(_word.value!!, onGuessCorrect = { _gameWon.value = true }, onGameOver = { _gameWon.value = false })
        }
    }

    fun doGuessOfWord(guess: String){
        _wordGuesses.value = _wordGuesses.value!!.plus(wordGuess.guessWord(guess))
    }
}

enum class Language {
    EN, SP, FR
}

private val WORDS = listOf("HELLO","BOATS","DOORS","NAILS","ABORT","GREED","GRIEF","QUIRK","QUOTE","RADAR","RELAX")