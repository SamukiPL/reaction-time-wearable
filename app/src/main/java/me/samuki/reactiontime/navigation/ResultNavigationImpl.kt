package me.samuki.reactiontime.navigation

import androidx.navigation.NavController
import me.samuki.reactiontime.features.home.presentation.HomeDestination
import me.samuki.reactiontime.features.resultScreens.ResultNavigation

class ResultNavigationImpl(override val navController: NavController) : ResultNavigation {
    override fun goForRetry(retryRoute: String) {
        navController.navigate(retryRoute) {
            popUpTo(HomeDestination.routeHome)
        }
    }

    override fun goToHome() {
        navController.navigate(HomeDestination.routeHome) {
            popUpTo(0)
        }
    }
}
