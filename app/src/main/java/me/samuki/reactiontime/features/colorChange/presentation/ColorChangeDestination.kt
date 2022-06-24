package me.samuki.reactiontime.features.colorChange.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import me.samuki.reactiontime.R
import me.samuki.reactiontime.navigation.AppNavigation
import me.samuki.reactiontime.presentation.ReactionDestination
import javax.inject.Inject

class ColorChangeDestination @Inject constructor() : ReactionDestination {
    override val screenName: Int get() = R.string.colorChangeTitle
    override val screenIcon: Int get() = R.drawable.ic_race_start

    override val routeName: String = routeColorChange
    override val arguments: List<NamedNavArgument> = emptyList()

    @Composable
    override fun BuildDestination(navigation: AppNavigation) {
        ColorChangeScreen(reactionNavigation = navigation)
    }

    companion object {
        const val routeColorChange = "colorChange"
    }
}
