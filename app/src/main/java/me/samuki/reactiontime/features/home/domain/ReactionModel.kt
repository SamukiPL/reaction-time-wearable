package me.samuki.reactiontime.features.home.domain

import androidx.annotation.StringRes

data class ReactionModel(
    @StringRes val name: Int,
    val route: String,
)
