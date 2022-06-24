package me.samuki.reactiontime.domain.reaction

interface ReactionRecorder {
    suspend fun resetRecording()
    fun startPrematureRecording()
    fun startCorrectRecording()
    suspend fun recordReaction(): ReactionStatus
}
