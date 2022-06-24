package me.samuki.reactiontime.navigation

import androidx.navigation.NavController
import me.samuki.reactiontime.features.baseReaction.ReactionNavigation
import me.samuki.reactiontime.features.home.presentation.HomeDestination
import me.samuki.reactiontime.features.resultScreens.failure.FailureDestination
import me.samuki.reactiontime.features.resultScreens.success.SuccessDestination

class ReactionNavigationImpl(private val navController: NavController) : ReactionNavigation {

    override fun goToFailure(retryRoute: String) {
        val route = FailureDestination.navigate(retryRoute)
        navController.navigate(route) {
            popUpTo(HomeDestination.routeHome)
        }
    }

    override fun goToSuccess(retryRoute: String, result: String) {
        val route = SuccessDestination.navigate(retryRoute, result)
        navController.navigate(route) {
            popUpTo(HomeDestination.routeHome)
        }
    }
}
