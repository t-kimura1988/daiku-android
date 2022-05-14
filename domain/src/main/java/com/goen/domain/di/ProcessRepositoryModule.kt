package com.goen.domain.di

import com.goen.domain.repository.GoalRepository
import com.goen.domain.repository.ProcessRepository
import com.goen.domain.repository.impl.GoalRepositoryImpl
import com.goen.domain.repository.impl.ProcessRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object ProcessRepositoryModule {
    @Provides
    fun provideProcessRepository(repository: ProcessRepositoryImpl): ProcessRepository {
        return repository
    }
}