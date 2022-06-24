package me.samuki.reactiontime.features.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import me.samuki.reactiontime.R
import me.samuki.reactiontime.features.home.domain.DashboardTileModel
import me.samuki.reactiontime.features.home.domain.ReactionModel
import me.samuki.reactiontime.features.home.presentation.list.ReactionCell

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    homeNavigation: HomeNavigation
) {
    LaunchedEffect(Unit) {
        viewModel.checkAverageTime()
        viewModel.getReactionsList()
    }
    val viewState = viewModel.viewState.value

    HomeContent(
        tiles = viewState.dashboardTiles,
        areTilesVisible = viewState.areTilesVisible,
        reactionsCount = viewState.reactionsList.size,
        reactionsList = viewState.reactionsList
    ) { route ->
        homeNavigation.goToTest(route)
    }
}

@Composable
fun HomeContent(
    tiles: List<DashboardTileModel>,
    areTilesVisible: Boolean,
    reactionsCount: Int,
    reactionsList: List<ReactionModel>,
    navigateToReaction: (String) -> Unit
) {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        autoCentering = AutoCenteringParams(itemIndex = 0),
        contentPadding = PaddingValues(0.dp)
    ) {
        if (areTilesVisible) {
            item {
                Results(tiles)
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Results(tiles: List<DashboardTileModel>) {
    val pagerState = rememberPagerState()
    Column {
        HorizontalPager(count = tiles.size, state = pagerState) { page ->
            val tile = tiles[page]
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    tile.icon(),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = tile.iconTint
                )
                Text(text = tile.text, fontSize = 24.sp)
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            activeColor = MaterialTheme.colors.primary,
            inactiveColor = Color.Gray
        )
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeContent(
        tiles = listOf(DashboardTileModel(
            "0.324",
            Color.Cyan,
        ) { Icons.Filled.EmojiEvents }),
        areTilesVisible = true,
        reactionsCount = 1,
        reactionsList = listOf(
            ReactionModel(
                R.string.app_name, R.drawable.ic_race_start, ""
            )
        )
    ) {}
}
