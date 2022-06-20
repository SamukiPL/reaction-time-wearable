package me.samuki.reactiontime.data.reaction

import javax.inject.Inject

class Stopwatch @Inject constructor() {
    private var startingTime: Long = -1

    fun start() {
        startingTime = System.currentTimeMillis()
    }

    fun stop(): Long {
        if (startingTime == -1L) {
            throw IllegalStateException("Start stopwatch before stopping it!")
        }
        return System.currentTimeMillis() - startingTime
    }
}
