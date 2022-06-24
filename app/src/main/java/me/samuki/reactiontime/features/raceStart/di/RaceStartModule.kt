package me.samuki.reactiontime.features.raceStart.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import me.samuki.reactiontime.features.raceStart.data.RaceStartProviderImpl
import me.samuki.reactiontime.features.raceStart.domain.RaceStartProvider

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RaceStartModule {
    @Binds
    abstract fun lightsProvider(raceStartProviderImpl: RaceStartProviderImpl): RaceStartProvider
}
