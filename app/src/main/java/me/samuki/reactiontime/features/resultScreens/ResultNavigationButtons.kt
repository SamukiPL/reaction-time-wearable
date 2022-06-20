package me.samuki.reactiontime.features.resultScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import me.samuki.reactiontime.presentation.theme.ReactionTimeTheme

@Composable
fun ResultNavigationButtons(onCancel: () -> Unit, onRetry: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Chip(onClick = onCancel, colors = ChipDefaults.chipColors(), role = Role.Image) {
            Icon(
                Icons.Filled.Cancel,
                contentDescription = null,
                Modifier.fillMaxSize(),
                tint = Color.White
            )
        }
        Chip(onClick = onRetry, colors = ChipDefaults.chipColors(), role = Role.Image) {
            Icon(
                Icons.Filled.RestartAlt,
                contentDescription = null,
                Modifier.fillMaxSize(),
                tint = Color.White
            )
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewSuccessScreen() {
    ReactionTimeTheme {
        ResultNavigationButtons(onCancel = {}) {

        }
    }
}
