package me.samuki.reactiontime.data.reaction

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class TestCountDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {

    var testsCount: Int
        get() = prefs.getInt(TESTS_PERFORMED_KEY, 0)
        private set(value) = prefs.edit(commit = true) {
            putInt(TESTS_PERFORMED_KEY, value)
        }

    fun incrementTestsCount(): Int {
        return ++testsCount
    }

    var failuresCount: Int
        get() = prefs.getInt(FAILED_TESTS_KEY, 0)
        private set(value) = prefs.edit(commit = true) {
            putInt(FAILED_TESTS_KEY, value)
        }

    fun incrementFailuresAndTestCount() {
        testsCount++
        failuresCount++
    }

    companion object {
        const val TESTS_PERFORMED_KEY = "TESTS_PERFORMED_KEY"
        const val FAILED_TESTS_KEY = "FAILED_TESTS_KEY"
    }
}
