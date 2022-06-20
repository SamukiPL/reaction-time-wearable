package me.samuki.reactiontime.domain

import kotlin.coroutines.CoroutineContext

abstract class UseCase(
    val context: CoroutineContext
)
