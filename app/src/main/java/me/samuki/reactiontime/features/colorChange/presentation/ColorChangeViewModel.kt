package me.samuki.reactiontime.features.colorChange.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.samuki.reactiontime.domain.reaction.RecordReactionUseCase
import me.samuki.reactiontime.features.baseReaction.ReactionViewModel
import me.samuki.reactiontime.features.colorChange.domain.ColorChangeState
import me.samuki.reactiontime.features.colorChange.domain.ObserveColorChangeUseCase
import me.samuki.reactiontime.util.ext.update
import javax.inject.Inject

@HiltViewModel
class ColorChangeViewModel @Inject constructor(
    recordReactionUseCase: RecordReactionUseCase,
    private val observeColorChangeUseCase: ObserveColorChangeUseCase
) : ReactionViewModel(recordReactionUseCase) {

    private val _viewState = mutableStateOf(ViewState())
    val viewState: State<ViewState> = _viewState

    fun startColorChange() {
        viewModelScope.launch {
            observeColorChangeUseCase().collect { colorState ->
                _viewState.update {
                    it.copy(
                        explanationVisible = colorState == ColorChangeState.Explanation,
                        backgroundColor = colorState.color
                    )
                }
            }
        }
    }

    data class ViewState(
        val explanationVisible: Boolean = true,
        val backgroundColor: Color = Color.Black
    )
}
