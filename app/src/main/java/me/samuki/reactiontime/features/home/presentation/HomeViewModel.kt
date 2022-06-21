package me.samuki.reactiontime.features.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.samuki.reactiontime.features.home.domain.GetTimesModelUseCase
import me.samuki.reactiontime.features.home.domain.GetReactionsListUseCase
import me.samuki.reactiontime.features.home.domain.ReactionModel
import me.samuki.reactiontime.util.EMPTY_STRING
import me.samuki.reactiontime.util.ext.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAverageTimeUseCase: GetTimesModelUseCase,
    private val getReactionsListUseCase: GetReactionsListUseCase
) : ViewModel() {

    private val _viewState = mutableStateOf(ViewState())
    val viewState: State<ViewState> = _viewState

    fun checkAverageTime() {
        viewModelScope.launch {
            val times = getAverageTimeUseCase()
            _viewState.update {
                it.copy(
                    averageTime = times?.averageTime.orEmpty(),
                    bestTime = times?.bestTime.orEmpty(),
                    timesVisible = times != null
                )
            }
        }
    }

    fun getReactionsList() {
        viewModelScope.launch {
            val list = getReactionsListUseCase()
            _viewState.update {
                it.copy(reactionsList = list)
            }
        }
    }

    data class ViewState(
        val averageTime: String = EMPTY_STRING,
        val bestTime: String = EMPTY_STRING,
        val timesVisible: Boolean = false,
        val reactionsList: List<ReactionModel> = emptyList()
    )
}
