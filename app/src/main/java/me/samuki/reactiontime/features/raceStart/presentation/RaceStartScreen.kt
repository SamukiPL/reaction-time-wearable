package me.samuki.reactiontime.features.raceStart.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.MaterialTheme
import me.samuki.reactiontime.features.resultScreens.failure.FailureDestination
import me.samuki.reactiontime.features.resultScreens.success.SuccessDestination

@Composable
fun RaceStartScreen(
    viewModel: RaceStartViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.startLights()
    }
    viewModel.viewEvent {
        when (it) {
            RaceStartViewModel.ViewEvent.Failure -> {
                val route = FailureDestination.navigate(RaceStartDestination.routeRaceStart)
                navController.navigate(route)
            }
            is RaceStartViewModel.ViewEvent.Success -> {
                val route = SuccessDestination.navigate(RaceStartDestination.routeRaceStart, it.result)
                navController.navigate(route)
            }
        }
    }

    val viewState = viewModel.viewState.value
    RaceStartContent(
        lightsOn = viewState.lightsOn,
    ) {
        viewModel.reactToLights()
    }
}

@Composable
fun RaceStartContent(
    lightsOn: Int,
    reactToLights: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .clickable { reactToLights() },
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Light(turnedOn = lightsOn >= 1)
            Light(turnedOn = lightsOn >= 2)
            Light(turnedOn = lightsOn >= 3)
        }
    }
}

@Composable
fun Light(turnedOn: Boolean) {
    val color = if (turnedOn) Color.Red else MaterialTheme.colors.background
    Box(modifier = Modifier.background(color).height(32.dp).width(32.dp)) {

    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    RaceStartContent(3) {

    }
}
