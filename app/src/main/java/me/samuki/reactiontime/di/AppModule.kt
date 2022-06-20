package me.samuki.reactiontime.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.samuki.reactiontime.io.IoCoroutineContext
import kotlin.random.Random

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun ioCoroutinesContext() = IoCoroutineContext()

    @Provides
    fun random(): Random = Random

    @Provides
    fun sharedPrefs(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("find_some_place_for_this_string", Context.MODE_PRIVATE)
}
