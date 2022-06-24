package me.samuki.reactiontime.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import me.samuki.reactiontime.navigation.AppNavigation

interface Destination {
    val routeName: String
    val arguments: List<NamedNavArgument>

    @Composable
    fun BuildDestination(navigation: AppNavigation)
}

interface ReactionDestination : Destination {
    @get:StringRes
    val screenName: Int

    @get:DrawableRes
    val screenIcon: Int
}
