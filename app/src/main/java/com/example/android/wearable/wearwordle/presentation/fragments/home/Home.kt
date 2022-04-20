package com.example.android.wearable.wearwordle.presentation.fragments.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SportsMartialArts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.android.wearable.wearwordle.R
import com.example.android.wearable.wearwordle.models.categoryList
import com.example.android.wearable.wearwordle.presentation.fragments.Paths

@Composable
fun Home(navHostController: NavHostController, listState: ScalingLazyListState){
    val chipModifier = Modifier

    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 32.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 32.dp
        ),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        state = listState
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                text = "Wear Wordle!"
            )
        }
        items(categoryList.size){ index ->
            val category = categoryList[index]
            Chip(
                modifier = chipModifier,
                label = {
                    Text(text = category.categoryLabel)
                },
                onClick = { navHostController.navigate(Paths.WORD) },
                icon = {
                    Icon(
                        imageVector = category.imageVector,
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

