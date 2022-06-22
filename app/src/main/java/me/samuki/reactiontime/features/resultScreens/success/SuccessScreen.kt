package me.samuki.reactiontime.features.resultScreens.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import me.samuki.reactiontime.features.home.presentation.HomeDestination
import me.samuki.reactiontime.features.resultScreens.ResultNavigationButtons
import me.samuki.reactiontime.presentation.theme.ReactionTimeTheme
import me.samuki.reactiontime.util.ext.getString

@Composable
fun SuccessScreen(navController: NavController) {
    val retryRoute = navController.getString(SuccessDestination.successTryAgain)
    val result = navController.getString(SuccessDestination.successResult) ?: ""

    SuccessContent(
        result = result,
        onCancel = {
            navController.navigate(HomeDestination.routeHome) {
                popUpTo(0)
            }
        },
        onRetry = {
            retryRoute?.let {
                navController.navigate(it) {
                    popUpTo(HomeDestination.routeHome)
                }
            }
        },
    )
}

@Composable
fun SuccessContent(result: String, onCancel: () -> Unit, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = result, fontSize = 28.sp)
        ResultNavigationButtons(onCancel, onRetry)
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewSuccessScreen() {
    ReactionTimeTheme {
        SuccessContent("0.325", onCancel = {}) {

        }
    }
}
