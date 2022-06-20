package me.samuki.reactiontime.features.resultScreens.success

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import me.samuki.reactiontime.presentation.Destination
import javax.inject.Inject

class SuccessDestination @Inject constructor() : Destination {
    override val routeName: String = successRoute
    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(successTryAgain) {
            type = NavType.StringType
        },
        navArgument(successResult) {
            type = NavType.StringType
        },
    )

    @Composable
    override fun BuildDestination(navController: NavController) {
        SuccessScreen(navController)
    }

    companion object {
        private const val successName = "success"
        const val successTryAgain = "tryAgain"
        const val successResult = "result"
        const val successRoute = "$successName/{$successTryAgain}/{$successResult}"

        fun navigate(tryAgain: String, result: String) = "$successName/$tryAgain/$result"
    }
}
