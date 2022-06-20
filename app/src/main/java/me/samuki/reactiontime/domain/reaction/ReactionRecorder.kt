package me.samuki.reactiontime.domain.reaction

import kotlinx.coroutines.flow.Flow

interface ReactionRecorder {
    val reactionStatus: Flow<ReactionStatus>
    suspend fun resetRecording()
    fun startPrematureRecording()
    fun startCorrectRecording()
    suspend fun recordReaction()
}
