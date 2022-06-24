package me.samuki.reactiontime.navigation

import androidx.navigation.NavController
import me.samuki.reactiontime.features.home.presentation.HomeNavigation

class HomeNavigationImpl(private val navController: NavController) : HomeNavigation {
    override fun goToTest(routeName: String) {
        navController.navigate(routeName)
    }
}
