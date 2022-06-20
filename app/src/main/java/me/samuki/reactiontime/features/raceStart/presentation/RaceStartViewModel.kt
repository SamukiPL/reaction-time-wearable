package me.samuki.reactiontime.features.raceStart.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.samuki.composableevent.Event
import me.samuki.composableevent.mutableEventOf
import me.samuki.reactiontime.domain.reaction.ReactionStatus
import me.samuki.reactiontime.domain.reaction.RecordReactionUseCase
import me.samuki.reactiontime.features.raceStart.domain.GetRaceStartUseCase
import javax.inject.Inject

@HiltViewModel
class RaceStartViewModel @Inject constructor(
    private val getRaceStartUseCase: GetRaceStartUseCase,
    private val recordReactionUseCase: RecordReactionUseCase
) : ViewModel() {

    private val _viewState = mutableStateOf(ViewState())
    val viewState: State<ViewState> = _viewState

    private val _viewEvent = mutableEventOf<ViewEvent>()
    val viewEvent: Event<ViewEvent> = _viewEvent

    fun startLights() {
        viewModelScope.launch {
            getRaceStartUseCase().collect {
                it.reactionStatus.manageStatus()

                _viewState.value = ViewState(
                    lightsOn = it.lightsOn,
                )
            }
        }
    }

    fun reactToLights() {
        viewModelScope.launch {
            recordReactionUseCase()
        }
    }

    private fun ReactionStatus.manageStatus(): Unit = when(this) {
        ReactionStatus.Awaiting -> {}
        ReactionStatus.Premature -> _viewEvent.sendEvent(ViewEvent.Failure)
        is ReactionStatus.Recorded -> _viewEvent.sendEvent(ViewEvent.Success(time))
    }

    data class ViewState(
        val lightsOn: Int = 0,
    )

    sealed interface ViewEvent {
        object Failure : ViewEvent
        data class Success(
            val result: String
        ) : ViewEvent
    }
}
