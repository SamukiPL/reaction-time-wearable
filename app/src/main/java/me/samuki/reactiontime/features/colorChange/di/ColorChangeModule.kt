package me.samuki.reactiontime.features.colorChange.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import me.samuki.reactiontime.features.colorChange.data.ColorChangeProviderImpl
import me.samuki.reactiontime.features.colorChange.domain.ColorChangeProvider

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ColorChangeModule {
    @Binds
    abstract fun colorChangeProvider(colorChangeProviderImpl: ColorChangeProviderImpl): ColorChangeProvider
}
