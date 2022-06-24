package me.samuki.reactiontime.features.colorChange.domain

import kotlinx.coroutines.flow.Flow

interface ColorChangeProvider {
    suspend fun observeColorChange(): Flow<ColorChangeState>
}
