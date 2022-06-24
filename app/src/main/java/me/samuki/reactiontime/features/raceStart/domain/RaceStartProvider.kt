package me.samuki.reactiontime.features.raceStart.domain

import kotlinx.coroutines.flow.Flow

interface RaceStartProvider {
    suspend fun observeLights(): Flow<RaceStartModel>
}
