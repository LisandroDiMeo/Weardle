package com.example.android.wearable.wearwordle.gamelogic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class WordGuess(private val word: String, private val onGuessCorrect : () -> Unit, private val onGameOver : () -> Unit = {}) {

    private var attempts = 0

    private fun toAnnotatedString(guessState : List<Pair<Char, Guess>>) : AnnotatedString {
        return guessState.foldRight(AnnotatedString("")) { pair, acc ->
            annotatedStringOf(pair) + acc
        }
    }

    private fun annotatedStringOf(p : Pair <Char, Guess>): AnnotatedString{
        return when(p.second){
            Guess.GREEN -> { AnnotatedString("${p.first}", GREEN_SPAN) }
            Guess.YELLOW -> { AnnotatedString("${p.first}", YELLOW_SPAN) }
            Guess.GRAY -> { AnnotatedString("${p.first}", GRAY_SPAN) }
        }
    }

    fun guessWord(guess: String) : AnnotatedString {
        attempts ++
        val guessResult = guess.mapIndexed { index, char ->
            when (char) {
                word[index] -> char to Guess.GREEN
                in word -> char to Guess.YELLOW
                else -> char to Guess.GRAY
            }
        }
        guessResult.filter { it.second != Guess.GREEN }.let { if ( it.isEmpty() ) onGuessCorrect.invoke() else if ( attempts == 5 ) onGameOver.invoke() }
        return toAnnotatedString(guessResult)
    }

    companion object {
        private val GREEN_SPAN = SpanStyle(Color.Green, 14.sp, FontWeight.Bold, FontStyle.Normal, FontSynthesis.All, FontFamily.Monospace)
        private val YELLOW_SPAN = SpanStyle(Color.Yellow, 14.sp, FontWeight.Bold, FontStyle.Normal, FontSynthesis.All, FontFamily.Monospace)
        private val GRAY_SPAN = SpanStyle(Color.Gray, 14.sp, FontWeight.Bold, FontStyle.Normal, FontSynthesis.All, FontFamily.Monospace)
    }

}

enum class Guess {
    GREEN, YELLOW, GRAY
}