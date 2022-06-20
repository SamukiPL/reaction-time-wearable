package me.samuki.reactiontime.features.raceStart.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import me.samuki.reactiontime.R
import me.samuki.reactiontime.presentation.Destination
import me.samuki.reactiontime.presentation.ReactionDestination
import javax.inject.Inject

class RaceStartDestination @Inject constructor() : ReactionDestination {
    override val routeName: String = routeRaceStart
    override val arguments: List<NamedNavArgument> = emptyList()

    override val screenName: Int = R.string.raceStartTitle
    override val screenIcon: Int
        get() = TODO("Not yet implemented")

    @Composable
    override fun BuildDestination(navController: NavController) {
        RaceStartScreen(navController = navController)
    }

    companion object {
        const val routeRaceStart = "raceStart"
    }
}
