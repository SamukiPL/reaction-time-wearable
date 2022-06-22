package me.samuki.reactiontime.data.reaction

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ReactionTimeFormatterTest : BehaviorSpec({

    lateinit var underTest: ReactionTimeFormatter

    given("ReactionTimeFormatter") {
        underTest = ReactionTimeFormatter()

        and("timestamp is 333 milliseconds") {
            val timestamp = 333L

            `when`("underTest.format gets invoked") {
                val formattedTime = underTest.format(timestamp)

                then("formattedTime is 0.333") {
                    formattedTime shouldBe "0.333"
                }
            }
        }

        and("timestamp is 2333 milliseconds") {
            val timestamp = 2333L

            `when`("underTest.format gets invoked") {
                val formattedTime = underTest.format(timestamp)

                then("formattedTime is 2.333") {
                    formattedTime shouldBe "2.333"
                }
            }
        }
    }
})
