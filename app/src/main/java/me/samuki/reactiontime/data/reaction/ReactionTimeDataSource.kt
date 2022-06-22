package me.samuki.reactiontime.data.reaction

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class ReactionTimeDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {
    fun saveTime(time: Long, testsCount: Int) {
        saveAverageTime(time, testsCount)
        manageBestTime(time)
    }

    var averageTime: Long
        get() = prefs.getLong(AVERAGE_TIME_KEY, -1)
        private set(value) = prefs.edit(commit = true) {
            putLong(AVERAGE_TIME_KEY, value)
        }

    var bestTime: Long
        get() = prefs.getLong(BEST_TIME_KEY, -1)
        private set(value) = prefs.edit(commit = true) {
            putLong(BEST_TIME_KEY, value)
        }

    fun saveAverageTime(time: Long, testsCount: Int) {
        averageTime = if (testsCount == 1) {
            time
        } else {
            val lastTime = averageTime * (testsCount - 1)
            (time + lastTime) / testsCount
        }
    }

    private fun manageBestTime(time: Long) {
        val lastBest = bestTime
        if (time < lastBest || lastBest == -1L) {
            bestTime = time
        }
    }

    companion object {
        const val AVERAGE_TIME_KEY = "AVERAGE_TIME_KEY"
        const val BEST_TIME_KEY = "BEST_TIME_KEY"
    }
}
