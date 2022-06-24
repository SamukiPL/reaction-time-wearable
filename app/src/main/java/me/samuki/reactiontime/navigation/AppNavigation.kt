package me.samuki.reactiontime.navigation

import androidx.navigation.NavController
import me.samuki.reactiontime.features.baseReaction.ReactionNavigation
import me.samuki.reactiontime.features.home.presentation.HomeNavigation
import me.samuki.reactiontime.features.resultScreens.ResultNavigation

class AppNavigation(navController: NavController) :
    HomeNavigation by HomeNavigationImpl(navController),
    ReactionNavigation by ReactionNavigationImpl(navController),
    ResultNavigation by ResultNavigationImpl(navController)
