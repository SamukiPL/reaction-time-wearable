package me.samuki.reactiontime.domain.reaction

import kotlinx.coroutines.withContext
import me.samuki.reactiontime.domain.UseCase
import me.samuki.reactiontime.io.IoCoroutineContext
import javax.inject.Inject

class RecordReactionUseCase @Inject constructor(
    ioCoroutineContext: IoCoroutineContext,
    private val reactionRecorder: ReactionRecorder
) : UseCase(ioCoroutineContext.context) {
    suspend operator fun invoke() = withContext(context) {
        reactionRecorder.recordReaction()
    }
}
