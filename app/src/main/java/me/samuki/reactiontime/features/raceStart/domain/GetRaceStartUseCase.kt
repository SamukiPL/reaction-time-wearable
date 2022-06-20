package me.samuki.reactiontime.features.raceStart.domain

import kotlinx.coroutines.withContext
import me.samuki.reactiontime.domain.UseCase
import me.samuki.reactiontime.io.IoCoroutineContext
import javax.inject.Inject

class GetRaceStartUseCase @Inject constructor(
    ioCoroutineContext: IoCoroutineContext,
    private val lightsProvider: LightsProvider
) : UseCase(ioCoroutineContext.context) {
    suspend operator fun invoke() = withContext(context) {
        lightsProvider.observeLights()
    }
}
