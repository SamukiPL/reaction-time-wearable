package me.samuki.reactiontime.features.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.samuki.reactiontime.features.home.domain.DashboardTileModel
import me.samuki.reactiontime.features.home.domain.GetDashboardTilesUseCase
import me.samuki.reactiontime.features.home.domain.GetReactionsListUseCase
import me.samuki.reactiontime.features.home.domain.ReactionModel
import me.samuki.reactiontime.util.ext.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAverageTimeUseCase: GetDashboardTilesUseCase,
    private val getReactionsListUseCase: GetReactionsListUseCase
) : ViewModel() {

    private val _viewState = mutableStateOf(ViewState())
    val viewState: State<ViewState> = _viewState

    fun checkAverageTime() {
        viewModelScope.launch {
            val tiles = getAverageTimeUseCase()
            _viewState.update {
                it.copy(
                    dashboardTiles = tiles
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
        val dashboardTiles: List<DashboardTileModel> = emptyList(),
        val reactionsList: List<ReactionModel> = emptyList()
    ) {
        val areTilesVisible get() = dashboardTiles.isNotEmpty()
    }
}
