package com.goen.domain.di

import com.goen.domain.datasource.GoalDatasource
import com.goen.domain.datasource.impl.GoalDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object GoalDatasourceModule {
    @Provides
    fun provideGoalDatasource(repository: GoalDatasourceImpl): GoalDatasource {
        return repository
    }
}