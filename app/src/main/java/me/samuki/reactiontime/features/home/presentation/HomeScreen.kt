package me.samuki.reactiontime.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import me.samuki.reactiontime.R
import me.samuki.reactiontime.features.home.domain.ReactionModel
import me.samuki.reactiontime.features.home.presentation.list.ReactionCell

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.checkAverageTime()
        viewModel.getReactionsList()
    }
    val viewState = viewModel.viewState.value

    HomeContent(
        averageTime = viewState.averageTime,
        averageTimeVisible = viewState.averageTimeVisible,
        reactionsCount = viewState.reactionsList.size,
        reactionsList = viewState.reactionsList
    ) { route ->
        navController.navigate(route)
    }
}

@Composable
fun HomeContent(
    averageTime: String,
    averageTimeVisible: Boolean,
    reactionsCount: Int,
    reactionsList: List<ReactionModel>,
    navigateToReaction: (String) -> Unit
) {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        autoCentering = AutoCenteringParams(itemIndex = 0)
    ) {
        if (averageTimeVisible) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                    Text(text = averageTime, fontSize = 24.sp)
                }
            }
        }
        items(count = reactionsCount) { index ->
            val model = reactionsList[index]
            ReactionCell(model = model) {
                navigateToReaction(model.route)
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeContent(
        averageTime = "0.333", averageTimeVisible = true, reactionsCount = 1, reactionsList = listOf(
            ReactionModel(
                R.string.app_name, ""
            )
        )
    ) {}
}
