package me.samuki.reactiontime.features.baseReaction

interface ReactionNavigation {
    fun goToFailure(retryRoute: String)
    fun goToSuccess(retryRoute: String, result: String)
}
