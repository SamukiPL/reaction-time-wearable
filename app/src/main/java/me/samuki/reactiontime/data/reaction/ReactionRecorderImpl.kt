package me.samuki.reactiontime.data.reaction

import me.samuki.reactiontime.domain.reaction.ObservationType
import me.samuki.reactiontime.domain.reaction.ReactionRecorder
import me.samuki.reactiontime.domain.reaction.ReactionStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReactionRecorderImpl @Inject constructor(
    private val stopwatch: Stopwatch,
    private val reactionTimeDataSource: ReactionTimeDataSource,
    private val testCountDataSource: TestCountDataSource,
    private val formatter: ReactionTimeFormatter
) : ReactionRecorder {
    private var observationType: ObservationType = ObservationType.Idle

    override suspend fun resetRecording() {
        observationType = ObservationType.Idle
        recordReaction()
    }

    override fun startPrematureRecording() {
        observationType = ObservationType.PreMature
    }

    override fun startCorrectRecording() {
        observationType = ObservationType.Correct
        stopwatch.start()
    }

    override suspend fun recordReaction(): ReactionStatus {
        return when (observationType) {
            ObservationType.Idle -> ReactionStatus.Awaiting
            ObservationType.PreMature -> {
                saveFailure()
                ReactionStatus.Premature
            }
            ObservationType.Correct -> {
                ReactionStatus.Recorded(time = saveTime())
            }
        }

    }

    private fun saveFailure() {
        testCountDataSource.incrementFailuresAndTestCount()
    }

    private fun saveTime(): String {
        val testsCount = testCountDataSource.incrementTestsCount()

        val time = stopwatch.stop()
        reactionTimeDataSource.saveTime(time, testsCount)
        return formatter.format(time)
    }
}
