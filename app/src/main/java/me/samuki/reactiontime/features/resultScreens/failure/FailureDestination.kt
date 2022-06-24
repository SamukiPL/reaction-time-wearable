package me.samuki.reactiontime.features.resultScreens.failure

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import me.samuki.reactiontime.navigation.AppNavigation
import me.samuki.reactiontime.presentation.Destination
import javax.inject.Inject

class FailureDestination @Inject constructor() : Destination {
    override val routeName: String = failureRoute
    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(failureTryAgain) {
            type = NavType.StringType
        },
    )

    @Composable
    override fun BuildDestination(navigation: AppNavigation) {
        FailureScreen(navigation)
    }

    companion object {
        private const val failureName = "failure"
        const val failureTryAgain = "tryAgain"
        const val failureRoute = "$failureName/{$failureTryAgain}"

        fun navigate(tryAgain: String) = "$failureName/$tryAgain"
    }
}
