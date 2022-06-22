package me.samuki.reactiontime.data.reaction

import android.content.SharedPreferences
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ReactionTimeDataSourceTest : BehaviorSpec({

    lateinit var underTest: ReactionTimeDataSource

    val prefs: SharedPreferences = mockk()
    val editor: SharedPreferences.Editor = mockk()

    beforeSpec {
        clearAllMocks()
        every { prefs.edit() } returns editor
        every { editor.putLong(any(), any()) } returns editor
        every { editor.commit() } returns true
    }

    given("ReactionTimeDataSource") {
        underTest = ReactionTimeDataSource(prefs)

        and("prefs.getLong for AVERAGE_TIME returns 500") {
            every { prefs.getLong(ReactionTimeDataSource.AVERAGE_TIME_KEY, any()) } returns 500L

            and("time = 600L and testCount = 3") {
                val time = 600L
                val testCount = 3

                `when`("underTest.saveAverageTime gets invoked") {
                    underTest.saveAverageTime(time, testCount)

                    then("editor.putLong gets invoked with 525") {
                        verify { editor.putLong(any(), 533) }
                    }
                }
            }

        }
    }
})
