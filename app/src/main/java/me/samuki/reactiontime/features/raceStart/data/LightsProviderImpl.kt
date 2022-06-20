package me.samuki.reactiontime.features.raceStart.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import me.samuki.reactiontime.domain.reaction.ReactionRecorder
import me.samuki.reactiontime.features.raceStart.domain.LightsProvider
import me.samuki.reactiontime.features.raceStart.domain.RaceStartModel
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Singleton
class LightsProviderImpl @Inject constructor(
    private val reactionRecorder: ReactionRecorder,
    private val random: Random
) : LightsProvider {
    override suspend fun observeLights(): Flow<RaceStartModel> {
        reactionRecorder.resetRecording()
        return lightsFlow().combine(reactionRecorder.reactionStatus) { lightsOn, reactionStatus ->
            RaceStartModel(lightsOn, reactionStatus)
        }
    }

    private fun lightsFlow() = flow {
        delay(1000)
        emit(1)

        delay(1000)
        emit(2)

        delay(1000)
        emit(3)
        reactionRecorder.startPrematureRecording()

        delay(random.nextLong(1, 10).toDuration(DurationUnit.SECONDS))
        reactionRecorder.startCorrectRecording()
        emit(0)
    }
}
