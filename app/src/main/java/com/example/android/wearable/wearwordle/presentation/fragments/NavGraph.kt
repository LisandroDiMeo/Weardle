package com.example.android.wearable.wearwordle.presentation.fragments

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import com.example.android.wearable.wearwordle.R
import com.example.android.wearable.wearwordle.presentation.fragments.home.Home
import com.example.android.wearable.wearwordle.presentation.fragments.word.CompleteWord
import com.example.android.wearable.wearwordle.presentation.viewmodels.CompleteWordViewModel

@Composable
fun NavGraph(
    onFinishActivity : () -> Unit,
    navController : NavHostController,
    listState: ScalingLazyListState,
    completeWordViewModel: CompleteWordViewModel
){

    SwipeDismissableNavHost(navController, startDestination = Paths.HOME){
        composable(Paths.HOME){
            BackHandler() { onFinishActivity() }
            Home(navController, listState)
        }
        composable(Paths.WORD){
            CompleteWord(navController, listState, completeWordViewModel)
        }
    }
}

object Paths {
    const val HOME = "home"
    const val WORD = "word"
}