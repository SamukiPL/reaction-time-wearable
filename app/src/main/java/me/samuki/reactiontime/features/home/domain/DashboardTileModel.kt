package me.samuki.reactiontime.features.home.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class DashboardTileModel(
    val text: String,
    val iconTint: Color,
    val icon: () -> ImageVector
)
