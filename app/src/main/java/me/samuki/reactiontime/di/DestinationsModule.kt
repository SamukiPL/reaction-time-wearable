package me.samuki.reactiontime.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import me.samuki.reactiontime.features.colorChange.presentation.ColorChangeDestination
import me.samuki.reactiontime.features.home.presentation.HomeDestination
import me.samuki.reactiontime.features.raceStart.presentation.RaceStartDestination
import me.samuki.reactiontime.features.resultScreens.failure.FailureDestination
import me.samuki.reactiontime.features.resultScreens.success.SuccessDestination
import me.samuki.reactiontime.presentation.Destination

@Module
@InstallIn(SingletonComponent::class)
abstract class DestinationsModule {
    @Binds
    @IntoSet
    abstract fun homeDestination(homeDestination: HomeDestination): Destination

    @Binds
    @IntoSet
    abstract fun raceStartDestination(raceStartDestination: RaceStartDestination): Destination

    @Binds
    @IntoSet
    abstract fun failureDestination(failureDestination: FailureDestination): Destination

    @Binds
    @IntoSet
    abstract fun successDestination(successDestination: SuccessDestination): Destination

    @Binds
    @IntoSet
    abstract fun colorChangeDestination(colorChangeDestination: ColorChangeDestination): Destination
}
