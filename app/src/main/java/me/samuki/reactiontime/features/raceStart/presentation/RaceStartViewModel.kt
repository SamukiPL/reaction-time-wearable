package me.samuki.reactiontime.features.raceStart.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.samuki.reactiontime.domain.reaction.RecordReactionUseCase
import me.samuki.reactiontime.features.baseReaction.ReactionViewModel
import me.samuki.reactiontime.features.raceStart.domain.GetRaceStartUseCase
import javax.inject.Inject

@HiltViewModel
class RaceStartViewModel @Inject constructor(
    private val getRaceStartUseCase: GetRaceStartUseCase,
    recordReactionUseCase: RecordReactionUseCase
) : ReactionViewModel(recordReactionUseCase) {

    private val _viewState = mutableStateOf(ViewState())
    val viewState: State<ViewState> = _viewState

    fun startLights() {
        viewModelScope.launch {
            getRaceStartUseCase().collect {
                _viewState.value = ViewState(
                    lightsOn = it.lightsOn,
                )
            }
        }
    }

    data class ViewState(
        val lightsOn: Int = 0,
    )
}
