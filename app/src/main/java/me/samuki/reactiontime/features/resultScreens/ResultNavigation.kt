package me.samuki.reactiontime.features.resultScreens

import androidx.navigation.NavController

interface ResultNavigation {
    val navController: NavController

    fun goForRetry(retryRoute: String)
    fun goToHome()
}
