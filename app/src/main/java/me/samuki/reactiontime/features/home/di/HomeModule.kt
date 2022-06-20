package me.samuki.reactiontime.features.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import me.samuki.reactiontime.features.home.data.HomeProviderImpl
import me.samuki.reactiontime.features.home.domain.HomeProvider

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class HomeModule {
    @Binds
    abstract fun homeProvider(homeProviderImpl: HomeProviderImpl): HomeProvider
}
