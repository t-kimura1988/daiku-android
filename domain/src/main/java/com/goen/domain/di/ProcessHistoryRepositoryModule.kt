package com.goen.domain.di

import com.goen.domain.repository.GoalRepository
import com.goen.domain.repository.ProcessHistoryRepository
import com.goen.domain.repository.ProcessRepository
import com.goen.domain.repository.impl.GoalRepositoryImpl
import com.goen.domain.repository.impl.ProcessHistoryRepositoryImpl
import com.goen.domain.repository.impl.ProcessRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object ProcessHistoryRepositoryModule {
    @Provides
    fun provideProcessRepository(repository: ProcessHistoryRepositoryImpl): ProcessHistoryRepository {
        return repository
    }
}