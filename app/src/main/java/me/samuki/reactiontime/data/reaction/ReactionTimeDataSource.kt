package me.samuki.reactiontime.data.reaction

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class ReactionTimeDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {
    fun saveTime(time: Long) {
        val lastTime = getAverageTime()
        val averageTime = (time + lastTime) / 2
        saveAverageTime(averageTime)
    }

    fun getAverageTime(): Long {
        return prefs.getLong(AVERAGE_TIME_KEY, -1)
    }

    private fun saveAverageTime(averageTime: Long) {
        prefs.edit(commit = true) {
            putLong(AVERAGE_TIME_KEY, averageTime)
        }
    }

    companion object {
        const val AVERAGE_TIME_KEY = "AVERAGE_TIME_KEY"
    }
}
