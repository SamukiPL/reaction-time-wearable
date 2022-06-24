package me.samuki.reactiontime.features.colorChange.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.samuki.reactiontime.domain.reaction.ReactionRecorder
import me.samuki.reactiontime.features.colorChange.domain.ColorChangeProvider
import me.samuki.reactiontime.features.colorChange.domain.ColorChangeState
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Singleton
class ColorChangeProviderImpl @Inject constructor(
    private val reactionRecorder: ReactionRecorder,
    private val random: Random
) : ColorChangeProvider {
    override suspend fun observeColorChange(): Flow<ColorChangeState> {
        reactionRecorder.resetRecording()
        return colorChangeFlow()
    }

    private fun colorChangeFlow() = flow {
        emit(ColorChangeState.Explanation)
        delay(2000)

        emit(ColorChangeState.Waiting)
        reactionRecorder.startPrematureRecording()

        delay(random.nextLong(1, 5).toDuration(DurationUnit.SECONDS))
        reactionRecorder.startCorrectRecording()
        emit(ColorChangeState.Ready)

    }
}
