package me.samuki.reactiontime.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.reactiontime.data.reaction.ReactionRecorderImpl
import me.samuki.reactiontime.domain.reaction.ReactionRecorder

@Module
@InstallIn(SingletonComponent::class)
abstract class ReactionModule {
    @Binds
    abstract fun reactionRecorder(reactionRecorderImpl: ReactionRecorderImpl): ReactionRecorder
}
