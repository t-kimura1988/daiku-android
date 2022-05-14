package com.goen.domain.di

import com.goen.domain.datasource.GoalDatasource
import com.goen.domain.datasource.GoalFavoriteDatasource
import com.goen.domain.datasource.impl.GoalDatasourceImpl
import com.goen.domain.datasource.impl.GoalFavoriteDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object GoalFavoriteDatasourceModule {
    @Provides
    fun provideGoalFavoriteDatasource(repository: GoalFavoriteDatasourceImpl): GoalFavoriteDatasource {
        return repository
    }
}