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

    private val charCounts = mutableMapOf<Char, Int>()

    init {
        word.forEach { char ->
            if(charCounts.containsKey(char))
                charCounts[char] = charCounts[char]!! + 1
            else
                charCounts[char] = 1
        }
    }

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
            else -> AnnotatedString("${p.first}", GRAY_SPAN)
        }
    }

    fun guessWord(guess: String) : AnnotatedString {
        attempts ++
        val auxCharCounts = mutableMapOf<Char, Int>()
        auxCharCounts.putAll(charCounts)
        val guessResult = guess.mapIndexed { index, char ->
            when (char) {
                word[index] -> char to Guess.GREEN
                !in word -> char to Guess.GRAY
                else -> char to Guess.MAYBE_YELLOW
            }
        }
        val guessResultWithCorrection = guessResult.map { pair ->
            if(pair.second == Guess.MAYBE_YELLOW){
                val countOfThisChar = auxCharCounts[pair.first]!!
                if(countOfThisChar == 1 && !guessResult.any { it.second == Guess.GREEN || it.second == Guess.YELLOW }){
                    auxCharCounts[pair.first] = 0
                    pair.first to Guess.YELLOW
                }else{
                    var greens = 0
                    guessResult.filter {it.first == pair.first}.forEach { if(it.second == Guess.GREEN) greens++ }
                    if(greens < countOfThisChar){
                        auxCharCounts[pair.first] = auxCharCounts[pair.first]!! - 1
                        pair.first to Guess.YELLOW
                    }else{
                        pair.first to Guess.GRAY
                    }
                }
            }
            else {
                pair
            }
        }

        guessResult.filter { it.second != Guess.GREEN }.let { if ( it.isEmpty() ) onGuessCorrect.invoke() else if ( attempts == 5 ) onGameOver.invoke() }
        return toAnnotatedString(guessResultWithCorrection)
    }

    companion object {
        private val GREEN_SPAN = SpanStyle(Color.Green, 14.sp, FontWeight.Bold, FontStyle.Normal, FontSynthesis.All, FontFamily.Monospace)
        private val YELLOW_SPAN = SpanStyle(Color.Yellow, 14.sp, FontWeight.Bold, FontStyle.Normal, FontSynthesis.All, FontFamily.Monospace)
        private val GRAY_SPAN = SpanStyle(Color.Gray, 14.sp, FontWeight.Bold, FontStyle.Normal, FontSynthesis.All, FontFamily.Monospace)
    }

}

enum class Guess {
    GREEN, YELLOW, GRAY, MAYBE_YELLOW
}