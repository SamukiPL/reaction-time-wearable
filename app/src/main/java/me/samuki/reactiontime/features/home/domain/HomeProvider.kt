package me.samuki.reactiontime.features.home.domain

interface HomeProvider {
    suspend fun getTiles(): List<DashboardTileModel>
    suspend fun getReactionsList(): List<ReactionModel>
}
