package me.samuki.reactiontime.features.colorChange.domain

import kotlinx.coroutines.withContext
import me.samuki.reactiontime.domain.UseCase
import me.samuki.reactiontime.io.IoCoroutineContext
import javax.inject.Inject

class ObserveColorChangeUseCase @Inject constructor(
    ioCoroutineContext: IoCoroutineContext,
    private val colorChangeProvider: ColorChangeProvider
) : UseCase(ioCoroutineContext.context) {
    suspend operator fun invoke() = withContext(context) {
        colorChangeProvider.observeColorChange()
    }
}
