package me.samuki.reactiontime.features.home.data

import me.samuki.reactiontime.data.reaction.ReactionTimeDataSource
import me.samuki.reactiontime.data.reaction.ReactionTimeFormatter
import me.samuki.reactiontime.features.home.domain.HomeProvider
import me.samuki.reactiontime.features.home.domain.ReactionModel
import me.samuki.reactiontime.features.home.domain.TimesModel
import me.samuki.reactiontime.presentation.Destination
import me.samuki.reactiontime.presentation.ReactionDestination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeProviderImpl @Inject constructor(
    private val reactionTimeDataSource: ReactionTimeDataSource,
    private val formatter: ReactionTimeFormatter,
    private val destinations: Set<@JvmSuppressWildcards Destination>,
) : HomeProvider {
    override suspend fun getTimes(): TimesModel? {
        val averageTime = reactionTimeDataSource.getAverageTime()
        val bestTime = reactionTimeDataSource.getBestTime()
        return if (averageTime >= 0) {
            TimesModel(
                averageTime = formatter.format(averageTime),
                bestTime = formatter.format(bestTime)
            )
        } else {
            null
        }
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
}
