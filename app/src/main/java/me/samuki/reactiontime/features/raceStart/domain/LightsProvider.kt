package me.samuki.reactiontime.features.raceStart.domain

import kotlinx.coroutines.flow.Flow

interface LightsProvider {
    suspend fun observeLights(): Flow<RaceStartModel>
}
