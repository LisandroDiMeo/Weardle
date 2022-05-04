package com.example.android.wearable.wearwordle.presentation.fragments.word

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.android.wearable.wearwordle.R
import com.example.android.wearable.wearwordle.presentation.fragments.Paths
import com.example.android.wearable.wearwordle.presentation.theme.Green200
import com.example.android.wearable.wearwordle.presentation.theme.gameResultsPalette
import com.example.android.wearable.wearwordle.presentation.viewmodels.symbols.GameStatus

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun GameResult(navHostController: NavHostController = rememberSwipeDismissableNavController(), gameResult: GameStatus = GameStatus.LOST, attempts: Int = 3){
    val stringId = if(GameStatus.WON == gameResult) R.string.game_result_won else R.string.game_result_lost
    val backgroundColor = if(GameStatus.WON == gameResult) gameResultsPalette.primary else gameResultsPalette.error
    val buttonBackgroundColor = if(GameStatus.WON == gameResult) gameResultsPalette.onPrimary else gameResultsPalette.onError
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = stringId, attempts).uppercase(),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            letterSpacing = 2.sp,
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                modifier = Modifier.padding(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonBackgroundColor),
                onClick = { navHostController.navigate(Paths.WORD) },
            ){
                Icon(
                    imageVector = Icons.Rounded.Refresh,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .wrapContentSize(align = Alignment.Center),
                )
            }
            Button(
                modifier = Modifier.padding(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonBackgroundColor),
                onClick = { navHostController.navigate(Paths.HOME) },
            ){
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .wrapContentSize(align = Alignment.Center),
                )
            }
        }

    }
}