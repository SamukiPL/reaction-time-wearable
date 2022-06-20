package me.samuki.reactiontime.features.resultScreens.failure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.wear.compose.material.Text
import me.samuki.reactiontime.R
import me.samuki.reactiontime.features.home.presentation.HomeDestination
import me.samuki.reactiontime.features.resultScreens.ResultNavigationButtons
import me.samuki.reactiontime.presentation.theme.ReactionTimeTheme
import me.samuki.reactiontime.util.ext.getString

@Composable
fun FailureScreen(navController: NavController) {
    val retryRoute = navController.getString(FailureDestination.failureTryAgain)

    FailureContent(
        onCancel = {
            navController.navigate(HomeDestination.routeHome, navOptions = NavOptions.Builder().setLaunchSingleTop(true).build())
        },
        onRetry = {
            retryRoute?.let { navController.navigate(retryRoute) }
        },
    )
}

@Composable
fun FailureContent(onCancel: () -> Unit, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xff9e2d3f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Too Fast", fontSize = 28.sp)
        ResultNavigationButtons(onCancel, onRetry)
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewSuccessScreen() {
    ReactionTimeTheme {
        FailureContent(onCancel = {}) {}
    }
}
