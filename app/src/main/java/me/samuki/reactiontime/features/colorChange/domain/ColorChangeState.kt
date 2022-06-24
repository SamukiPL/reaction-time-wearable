package me.samuki.reactiontime.features.colorChange.domain

import androidx.compose.ui.graphics.Color

enum class ColorChangeState(val color: Color) {
    Explanation(Color.Black), Waiting(Color.Red), Ready(Color.Green)
}
