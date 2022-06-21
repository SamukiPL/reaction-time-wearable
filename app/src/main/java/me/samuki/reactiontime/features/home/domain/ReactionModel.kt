package me.samuki.reactiontime.features.home.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ReactionModel(
    @StringRes val name: Int,
    @DrawableRes val icon: Int,
    val route: String,
)
