package me.samuki.reactiontime.features.home.data

import me.samuki.reactiontime.data.reaction.ReactionTimeDataSource
import me.samuki.reactiontime.data.reaction.ReactionTimeFormatter
import me.samuki.reactiontime.features.home.domain.HomeProvider
import me.samuki.reactiontime.features.home.domain.ReactionModel
import me.samuki.reactiontime.presentation.Destination
import me.samuki.reactiontime.presentation.ReactionDestination
import me.samuki.reactiontime.util.EMPTY_STRING
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeProviderImpl @Inject constructor(
    private val reactionTimeDataSource: ReactionTimeDataSource,
    private val formatter: ReactionTimeFormatter,
    private val destinations: Set<@JvmSuppressWildcards Destination>,
) : HomeProvider {
    override suspend fun getAverageTime(): String {
        val averageTime = reactionTimeDataSource.getAverageTime()
        return if (averageTime >= 0) {
            formatter.format(averageTime)
        } else {
            EMPTY_STRING
        }
    }

    override suspend fun getReactionsList(): List<ReactionModel> {
        val reactionDestinations = destinations.filterIsInstance<ReactionDestination>()
        return reactionDestinations.map { ReactionModel(
            name = it.screenName,
            route = it.routeName
        ) }
    }
}
