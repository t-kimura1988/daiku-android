package com.goen.domain.di

import com.goen.domain.repository.StoryCharacterRepository
import com.goen.domain.repository.impl.StoryCharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object StoryCharacterRepositoryModule {
    @Provides
    fun provideStoryCharacterRepository(repository: StoryCharacterRepositoryImpl): StoryCharacterRepository {
        return repository
    }
}