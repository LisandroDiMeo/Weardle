package com.example.android.wearable.wearwordle.presentation

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.android.wearable.wearwordle.presentation.fragments.NavGraph
import com.example.android.wearable.wearwordle.presentation.theme.WearAppTheme
import com.example.android.wearable.wearwordle.presentation.viewmodels.CompleteWordViewModel

@Composable
fun WearWordleApp(completeWordViewModel: CompleteWordViewModel, onCloseApp: () -> Unit){
    val globalNav = rememberSwipeDismissableNavController()
    val listState = rememberScalingLazyListState()
    WearAppTheme {
        Scaffold(
            timeText = { TimeText() },
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            positionIndicator = {  }
        ) {
            NavGraph(onFinishActivity = onCloseApp, navController = globalNav, listState, completeWordViewModel)
        }
    }
}