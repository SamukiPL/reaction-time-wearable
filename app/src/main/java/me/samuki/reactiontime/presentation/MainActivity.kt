/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package me.samuki.reactiontime.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import dagger.hilt.android.AndroidEntryPoint
import me.samuki.reactiontime.features.home.presentation.HomeDestination
import me.samuki.reactiontime.presentation.theme.ReactionTimeTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var destinations: Set<@JvmSuppressWildcards Destination>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberSwipeDismissableNavController()
            ReactionTimeTheme {
                SwipeDismissableNavHost(
                    navController = navController,
                    startDestination = HomeDestination.routeHome
                ) {
                    destinations.forEach { destination ->
                        composable(destination.routeName, arguments = destination.arguments) {
                            destination.BuildDestination(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
