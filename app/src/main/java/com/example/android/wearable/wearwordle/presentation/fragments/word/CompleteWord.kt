package com.example.android.wearable.wearwordle.presentation.fragments.word

import android.app.RemoteInput
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContactSupport
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.wear.compose.material.*
import androidx.wear.compose.material.CardDefaults
import androidx.wear.input.RemoteInputIntentHelper
import com.example.android.wearable.wearwordle.database.entities.Word
import com.example.android.wearable.wearwordle.models.categoryList
import com.example.android.wearable.wearwordle.presentation.fragments.Paths
import com.example.android.wearable.wearwordle.presentation.viewmodels.CompleteWordViewModel
import com.example.android.wearable.wearwordle.presentation.viewmodels.symbols.GameStatus
import com.example.android.wearable.wearwordle.presentation.viewmodels.symbols.Language
import java.lang.Exception

@Composable
fun CompleteWord(navHostController: NavHostController, listState: ScalingLazyListState, completeWordViewModel: CompleteWordViewModel, language: Language){

    val wordGuesses by completeWordViewModel.wordGuesses.observeAsState(listOf())
    val wordToGuess by completeWordViewModel.fetchWordFromLanguage(language).collectAsState(Word(-1,""))
    val gameResult by completeWordViewModel.gameStatus.observeAsState(GameStatus.PLAYING)

    LaunchedEffect(!wordToGuess.word.isNullOrEmpty()){
        completeWordViewModel.startGuessingWithWord(wordToGuess.word!!)
    }
    if(gameResult != GameStatus.PLAYING){
        LaunchedEffect(Unit){
            navHostController.navigate("gameover?language=${language}")
        }
    }

    val remoteKeyboardLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){ inputActivityResult ->
        inputActivityResult.data.let { remoteInputIntent ->
            // TODO: Improve this Try catch.
            try {
                val remoteInputBundle = RemoteInput.getResultsFromIntent(remoteInputIntent)
                val wordGuess = remoteInputBundle.getCharSequence("word_guess")
                completeWordViewModel.doGuessOfWord(wordGuess.toString().uppercase())
            }
            catch (e : Exception){
                println(e.message)
            }
        }
    }

    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 32.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 32.dp
        ),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(wordGuesses.size){ guessIndex ->
            WordTryHolder(wordGuesses[guessIndex])
        }
        item {
            Chip(
                modifier = Modifier,
                label = {
                    Text(text = "Guess")
                },
                onClick = {
                    val intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
                    val remoteInputs: List<RemoteInput> = listOf(RemoteInput.Builder("word_guess").setLabel("Your Guess").build())
                    RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)
                    remoteKeyboardLauncher.launch(intent)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.ContactSupport,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .wrapContentSize(align = Alignment.Center),
                    )
                }
            )
        }
    }
}

@Composable
fun WordTryHolder(guessAnnotated: AnnotatedString){
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundPainter = CardDefaults.cardBackgroundPainter(Color(0xFF828485), Color(0xFFFFFFFF))
    ) {
        Text(text = guessAnnotated,
            modifier = Modifier.fillMaxWidth(),
            letterSpacing = 10.sp, textAlign = TextAlign.Center)
    }
}