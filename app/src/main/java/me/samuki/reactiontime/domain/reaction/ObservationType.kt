package me.samuki.reactiontime.domain.reaction

sealed interface ObservationType {
    object Idle: ObservationType
    object PreMature: ObservationType
    object Correct: ObservationType
}
