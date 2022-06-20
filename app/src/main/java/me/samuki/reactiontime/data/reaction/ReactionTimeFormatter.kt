package me.samuki.reactiontime.data.reaction

import javax.inject.Inject

class ReactionTimeFormatter @Inject constructor() {
    fun format(timestamp: Long): String {
        val milliseconds = (timestamp % 1000).pad(3)
        val seconds = (timestamp / 1000).pad(1)
        return "$seconds.$milliseconds"
    }

    private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')
}
