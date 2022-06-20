package me.samuki.reactiontime.io

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

data class IoCoroutineContext(
    val context: CoroutineContext = Dispatchers.IO
)
