package me.samuki.reactiontime.features.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.samuki.reactiontime.features.home.domain.GetAverageTimeUseCase
import me.samuki.reactiontime.features.home.domain.GetReactionsListUseCase
import me.samuki.reactiontime.features.home.domain.ReactionModel
import me.samuki.reactiontime.util.ext.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAverageTimeUseCase: GetAverageTimeUseCase,
    private val getReactionsListUseCase: GetReactionsListUseCase
) : ViewModel() {

    private val _viewState = mutableStateOf(ViewState())
    val viewState: State<ViewState> = _viewState

    fun checkAverageTime() {
        viewModelScope.launch {
            val averageTime = getAverageTimeUseCase()
            _viewState.update {
                it.copy(
                    averageTime = averageTime,
                    averageTimeVisible = averageTime.isNotEmpty()
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
        val averageTime: String = "",
        val averageTimeVisible: Boolean = false,
        val reactionsList: List<ReactionModel> = emptyList()
    )
}
