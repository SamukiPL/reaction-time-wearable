package me.samuki.reactiontime.domain.reaction

sealed interface ReactionStatus {
    object Awaiting : ReactionStatus
    object Premature: ReactionStatus
    data class Recorded(
        val time: String
    ) : ReactionStatus
}
