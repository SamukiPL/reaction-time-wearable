package me.samuki.reactiontime.features.raceStart.domain

import me.samuki.reactiontime.domain.reaction.ReactionStatus

data class RaceStartModel(
    val lightsOn: Int,
    val reactionStatus: ReactionStatus
)
