package com.goen.domain.di

import com.goen.domain.datasource.StoryDatasource
import com.goen.domain.datasource.impl.StoryDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object StoryDatasourceModule {
    @Provides
    fun provideStoryDatasource(repository: StoryDatasourceImpl): StoryDatasource {
        return repository
    }
}