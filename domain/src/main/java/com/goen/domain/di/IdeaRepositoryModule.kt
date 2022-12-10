package com.goen.domain.di

import com.goen.domain.repository.IdeaRepository
import com.goen.domain.repository.impl.IdeaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object IdeaRepositoryModule {
    @Provides
    fun provideIdeaRepository(repository: IdeaRepositoryImpl): IdeaRepository {
        return repository
    }
}