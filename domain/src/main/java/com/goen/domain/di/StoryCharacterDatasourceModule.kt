package com.goen.domain.di

import com.goen.domain.datasource.StoryCharacterDatasource
import com.goen.domain.datasource.impl.StoryCharacterDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object StoryCharacterDatasourceModule {
    @Provides
    fun provideStoryCharacterDatasource(repository: StoryCharacterDatasourceImpl): StoryCharacterDatasource {
        return repository
    }
}