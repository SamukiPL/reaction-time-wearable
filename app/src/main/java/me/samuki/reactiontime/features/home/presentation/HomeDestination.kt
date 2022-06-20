package me.samuki.reactiontime.features.home.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import me.samuki.reactiontime.presentation.Destination
import javax.inject.Inject

class HomeDestination @Inject constructor() : Destination {
    override val routeName: String = routeHome
    override val arguments: List<NamedNavArgument> = emptyList()

    @Composable
    override fun BuildDestination(navController: NavController) {
        HomeScreen(navController = navController)
    }

    companion object {
        const val routeHome = "home"
    }
}
