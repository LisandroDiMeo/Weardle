package com.example.android.wearable.wearwordle.presentation.fragments

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.SwipeProgress
import androidx.wear.compose.material.SwipeableDefaults
import androidx.wear.compose.material.SwipeableState
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.android.wearable.wearwordle.presentation.fragments.home.Home
import com.example.android.wearable.wearwordle.presentation.fragments.word.CompleteWord
import com.example.android.wearable.wearwordle.presentation.fragments.word.GameResult
import com.example.android.wearable.wearwordle.presentation.viewmodels.CompleteWordViewModel
import com.example.android.wearable.wearwordle.presentation.viewmodels.symbols.Language

@Composable
fun NavGraph(
    onFinishActivity : () -> Unit,
    navController : NavHostController,
    listState: ScalingLazyListState,
    completeWordViewModel: CompleteWordViewModel
){

    SwipeDismissableNavHost(navController, startDestination = Paths.HOME){
        composable(Paths.HOME){
            completeWordViewModel.resetGameState()
            BackHandler { onFinishActivity() }
            Home(navController, listState)
        }
        composable(
            Paths.WORD,
            arguments = listOf(navArgument("language") { defaultValue = "EN" })
        ){ navBackStackEntry ->
            BackHandler { navController.navigate(Paths.HOME) }
            CompleteWord(navController, listState, completeWordViewModel, navBackStackEntry.arguments?.getString("language")!!.toLanguage())
        }
        composable(
            Paths.GAMEOVER,
            arguments = listOf(navArgument("language") { defaultValue = "EN" })
        ){ navBackStackEntry ->
            BackHandler { navController.navigate(Paths.HOME) }
            GameResult(navController, completeWordViewModel, completeWordViewModel.gameStatus.value!!, completeWordViewModel.attempts(), navBackStackEntry.arguments?.getString("language")!!.toLanguage())
        }
    }
}

object Paths {
    const val HOME = "home"
    const val WORD = "word?language={language}"
    const val GAMEOVER = "gameover?language={language}"
}

fun String.toLanguage() =
    when(this){
        "EN" -> Language.EN
        "SP" -> Language.SP
        "FR" -> Language.FR
        else -> Language.EN
    }
