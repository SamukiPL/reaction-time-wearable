package me.samuki.reactiontime.features.home.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LineAxis
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.ui.graphics.Color
import me.samuki.reactiontime.data.reaction.ReactionTimeDataSource
import me.samuki.reactiontime.data.reaction.ReactionTimeFormatter
import me.samuki.reactiontime.data.reaction.TestCountDataSource
import me.samuki.reactiontime.features.home.domain.DashboardTileModel
import me.samuki.reactiontime.features.home.domain.HomeProvider
import me.samuki.reactiontime.features.home.domain.ReactionModel
import me.samuki.reactiontime.presentation.Destination
import me.samuki.reactiontime.presentation.ReactionDestination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeProviderImpl @Inject constructor(
    private val reactionTimeDataSource: ReactionTimeDataSource,
    private val testCountDataSource: TestCountDataSource,
    private val formatter: ReactionTimeFormatter,
    private val destinations: Set<@JvmSuppressWildcards Destination>,
) : HomeProvider {
    override suspend fun getTiles(): List<DashboardTileModel> {
        return generateTimeTiles() + generateTestsCountTiles()
    }

    override suspend fun getReactionsList(): List<ReactionModel> {
        val reactionDestinations = destinations.filterIsInstance<ReactionDestination>()
        return reactionDestinations.map {
            ReactionModel(
                name = it.screenName,
                icon = it.screenIcon,
                route = it.routeName
            )
        }
    }

    private fun generateTimeTiles(): List<DashboardTileModel> {
        val averageTime = reactionTimeDataSource.averageTime
        val bestTime = reactionTimeDataSource.bestTime
        return if (averageTime != -1L) {
            listOf(
                DashboardTileModel(
                    text = formatter.format(bestTime),
                    iconTint = Color(0xFFFFBF00)
                ) { Icons.Filled.EmojiEvents },
                DashboardTileModel(
                    text = formatter.format(averageTime),
                    iconTint = Color(0xFF60ABEE)
                ) { Icons.Filled.LineAxis }
            )
        } else emptyList()
    }

    private fun generateTestsCountTiles(): List<DashboardTileModel> {
        val failuresCount = testCountDataSource.failuresCount
        return if (failuresCount > 0) {
            val testsCount = testCountDataSource.testsCount

            listOf(
                DashboardTileModel(
                    text = "$failuresCount/$testsCount",
                    iconTint = Color(0xFFE62000)
                ) { Icons.Filled.ThumbDown }
            )
        } else emptyList()
    }
}
