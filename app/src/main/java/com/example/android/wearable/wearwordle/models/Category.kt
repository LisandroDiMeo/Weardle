package com.example.android.wearable.wearwordle.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*

data class Category (val categoryLabel: String, val categoryIcon: String){
    val imageVector = when(categoryIcon){
        "FRENCH" -> Icons.Rounded.BakeryDining
        "SPANISH" -> Icons.Rounded.Tapas
        "ENGLISH" -> Icons.Rounded.OutdoorGrill
        else -> Icons.Rounded.HealthAndSafety
    }
}

val categoryList = listOf(
    Category("French", "FRENCH"),
    Category("Spanish", "SPANISH"),
    Category("English", "ENGLISH"),
)