package me.samuki.reactiontime.data.reaction

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.samuki.reactiontime.domain.reaction.ObservationType
import me.samuki.reactiontime.domain.reaction.ReactionRecorder
import me.samuki.reactiontime.domain.reaction.ReactionStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReactionRecorderImpl @Inject constructor(
    private val stopwatch: Stopwatch,
    private val reactionTimeDataSource: ReactionTimeDataSource,
    private val formatter: ReactionTimeFormatter
) : ReactionRecorder {
    private var observationType: ObservationType = ObservationType.Idle

    private val _reactionStatus = MutableStateFlow<ReactionStatus>(ReactionStatus.Awaiting)
    override val reactionStatus: StateFlow<ReactionStatus> = _reactionStatus

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

    override suspend fun recordReaction() {
        _reactionStatus.emit(
            when (observationType) {
                ObservationType.Idle -> ReactionStatus.Awaiting
                ObservationType.PreMature -> ReactionStatus.Premature
                ObservationType.Correct -> {
                    ReactionStatus.Recorded(time = saveTime())
                }
            }
        )
    }

    private fun saveTime(): String {
        val time = stopwatch.stop()
        reactionTimeDataSource.saveTime(time)
        return formatter.format(time)
    }
}
