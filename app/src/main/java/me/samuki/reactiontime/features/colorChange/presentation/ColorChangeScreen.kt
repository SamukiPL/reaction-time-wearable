package me.samuki.reactiontime.features.colorChange.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Text
import me.samuki.reactiontime.R
import me.samuki.reactiontime.features.baseReaction.ReactionNavigation
import me.samuki.reactiontime.features.baseReaction.ReactionViewModel

@Composable
fun ColorChangeScreen(
    viewModel: ColorChangeViewModel = hiltViewModel(),
    reactionNavigation: ReactionNavigation
) {
    LaunchedEffect(Unit) {
        viewModel.startColorChange()
    }
    viewModel.reactionEvent { event ->
        when (event) {
            ReactionViewModel.ReactionEvent.Failure -> reactionNavigation.goToFailure(
                ColorChangeDestination.routeColorChange
            )
            is ReactionViewModel.ReactionEvent.Success -> reactionNavigation.goToSuccess(
                ColorChangeDestination.routeColorChange,
                event.result
            )
        }
    }

    val viewState = viewModel.viewState.value
    ColorChangeContent(
        showExplanation = viewState.explanationVisible,
        backgroundColor = viewState.backgroundColor
    ) {
        viewModel.react()
    }
}

@Composable
fun ColorChangeContent(
    showExplanation: Boolean,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        if (showExplanation) {
            Text(
                text = stringResource(id = R.string.colorChangeExplanation),
                Modifier.align(Alignment.Center),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewHomeScreenExplanation() {
    ColorChangeContent(true, Color.Black) {}
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewHomeScreenWait() {
    ColorChangeContent(false, Color.Red) {}
}
