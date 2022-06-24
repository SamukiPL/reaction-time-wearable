package me.samuki.reactiontime.features.baseReaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.samuki.composableevent.Event
import me.samuki.composableevent.mutableEventOf
import me.samuki.reactiontime.domain.reaction.ReactionStatus
import me.samuki.reactiontime.domain.reaction.RecordReactionUseCase

abstract class ReactionViewModel(
    private val recordReactionUseCase: RecordReactionUseCase
) : ViewModel() {

    private val _reactionEvent = mutableEventOf<ReactionEvent>()
    val reactionEvent: Event<ReactionEvent> = _reactionEvent

    fun react() {
        viewModelScope.launch {
            recordReactionUseCase().manageStatus()
        }
    }

    private fun ReactionStatus.manageStatus(): Unit = when (this) {
        ReactionStatus.Awaiting -> {}
        ReactionStatus.Premature -> _reactionEvent.sendEvent(ReactionEvent.Failure)
        is ReactionStatus.Recorded -> _reactionEvent.sendEvent(ReactionEvent.Success(time))
    }

    sealed interface ReactionEvent {
        object Failure : ReactionEvent
        data class Success(
            val result: String
        ) : ReactionEvent
    }
}
