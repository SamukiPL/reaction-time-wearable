package me.samuki.reactiontime.features.raceStart.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.samuki.reactiontime.domain.reaction.ReactionRecorder
import me.samuki.reactiontime.features.raceStart.domain.RaceStartModel
import me.samuki.reactiontime.features.raceStart.domain.RaceStartProvider
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Singleton
class RaceStartProviderImpl @Inject constructor(
    private val reactionRecorder: ReactionRecorder,
    private val random: Random
) : RaceStartProvider {
    override suspend fun observeLights(): Flow<RaceStartModel> {
        reactionRecorder.resetRecording()
        return lightsLightingFlow().map { lightsOn ->
            RaceStartModel(lightsOn)
        }
    }

    private fun lightsLightingFlow() = flow {
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
