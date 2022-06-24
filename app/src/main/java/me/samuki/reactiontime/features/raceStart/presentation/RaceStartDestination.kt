package me.samuki.reactiontime.features.raceStart.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import me.samuki.reactiontime.R
import me.samuki.reactiontime.navigation.AppNavigation
import me.samuki.reactiontime.presentation.ReactionDestination
import javax.inject.Inject

class RaceStartDestination @Inject constructor() : ReactionDestination {
    override val routeName: String = routeRaceStart
    override val arguments: List<NamedNavArgument> = emptyList()

    override val screenName: Int = R.string.raceStartTitle
    override val screenIcon: Int = R.drawable.ic_race_start

    @Composable
    override fun BuildDestination(navigation: AppNavigation) {
        RaceStartScreen(reactionNavigation = navigation)
    }

    companion object {
        const val routeRaceStart = "raceStart"
    }
}
