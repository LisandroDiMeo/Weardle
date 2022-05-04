package com.example.android.wearable.wearwordle.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Red400 = Color(0xFFCF6679)

val Green400 = Color(0xff03a56a)
val Green200 = Color(0xFFB9F6CA)
val Red200 = Color(0xFFD50000)

internal var gameResultsPalette: Colors = Colors(
    primary = Green400,
    error = Red200,
    onPrimary = Green200,
    onSecondary = Color.White,
    onError = Color.White
)

internal val wearColorPalette: Colors = Colors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant = Teal200,
    error = Red400,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onError = Color.Black
)
