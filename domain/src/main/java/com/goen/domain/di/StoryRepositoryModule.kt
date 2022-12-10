package com.goen.domain.di

import com.goen.domain.repository.StoryRepository
import com.goen.domain.repository.impl.StoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object StoryRepositoryModule {
    @Provides
    fun provideStoryRepository(repository: StoryRepositoryImpl): StoryRepository {
        return repository
    }
}