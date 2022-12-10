package com.goen.domain.di

import com.goen.domain.repository.MakiRepository
import com.goen.domain.repository.impl.MakiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object MakiRepositoryModule {
    @Provides
    fun provideIdeaRepository(repository: MakiRepositoryImpl): MakiRepository {
        return repository
    }
}