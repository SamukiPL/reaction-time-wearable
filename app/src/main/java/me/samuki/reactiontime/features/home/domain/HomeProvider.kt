package me.samuki.reactiontime.features.home.domain

interface HomeProvider {
    suspend fun getAverageTime(): String
    suspend fun getReactionsList(): List<ReactionModel>
}
