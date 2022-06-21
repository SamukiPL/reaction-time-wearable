package me.samuki.reactiontime.data.reaction

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class ReactionTimeDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {
    fun saveTime(time: Long) {
        saveAverageTime(time)
        manageBestTime(time)
    }

    fun getAverageTime(): Long {
        return prefs.getLong(AVERAGE_TIME_KEY, -1)
    }

    fun getBestTime(): Long {
        return prefs.getLong(BEST_TIME_KEY, -1)
    }

    private fun saveAverageTime(time: Long) {
        val lastTime = getAverageTime()
        val averageTime = (time + lastTime) / 2
        prefs.edit(commit = true) {
            putLong(AVERAGE_TIME_KEY, averageTime)
        }
    }

    private fun manageBestTime(time: Long) {
        val lastBest = getBestTime()
        if (time < lastBest || lastBest == -1L) {
            prefs.edit(commit = true) {
                putLong(BEST_TIME_KEY, time)
            }
        }
    }

    companion object {
        const val AVERAGE_TIME_KEY = "AVERAGE_TIME_KEY"
        const val BEST_TIME_KEY = "BEST_TIME_KEY"
    }
}
