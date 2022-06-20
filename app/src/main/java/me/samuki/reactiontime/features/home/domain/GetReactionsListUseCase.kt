package me.samuki.reactiontime.features.home.domain

import kotlinx.coroutines.withContext
import me.samuki.reactiontime.domain.UseCase
import me.samuki.reactiontime.io.IoCoroutineContext
import javax.inject.Inject

class GetReactionsListUseCase @Inject constructor(
    ioBackgroundContext: IoCoroutineContext,
    private val homeProvider: HomeProvider
) : UseCase(ioBackgroundContext.context) {
    suspend operator fun invoke() = withContext(context) {
        homeProvider.getReactionsList()
    }
}
