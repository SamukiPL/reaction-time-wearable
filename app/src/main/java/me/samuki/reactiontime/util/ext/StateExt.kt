package me.samuki.reactiontime.util.ext

import androidx.compose.runtime.MutableState

fun <T> MutableState<T>.update(block: (T) -> T) {
    value = block(value)
}
