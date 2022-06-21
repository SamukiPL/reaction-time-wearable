package me.samuki.reactiontime.features.home.domain

import kotlinx.coroutines.withContext
import me.samuki.reactiontime.domain.UseCase
import me.samuki.reactiontime.io.IoCoroutineContext
import javax.inject.Inject

class GetTimesModelUseCase @Inject constructor(
    ioCoroutineContext: IoCoroutineContext,
    private val homeProvider: HomeProvider
) : UseCase(ioCoroutineContext.context){
    suspend operator fun invoke() = withContext(context) {
        homeProvider.getTimes()
    }
}
