package com.goen.domain.di

import com.goen.domain.repository.GoalFavoriteRepository
import com.goen.domain.repository.GoalRepository
import com.goen.domain.repository.impl.GoalFavoriteRepositoryImpl
import com.goen.domain.repository.impl.GoalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object GoalFavoriteRepositoryModule {
    @Provides
    fun provideGoalRepository(repository: GoalFavoriteRepositoryImpl): GoalFavoriteRepository {
        return repository
    }
}