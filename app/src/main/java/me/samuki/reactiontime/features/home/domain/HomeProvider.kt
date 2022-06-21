package me.samuki.reactiontime.features.home.domain

interface HomeProvider {
    suspend fun getTimes(): TimesModel?
    suspend fun getReactionsList(): List<ReactionModel>
}
