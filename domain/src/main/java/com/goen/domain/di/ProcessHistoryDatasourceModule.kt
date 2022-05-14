package com.goen.domain.di

import com.goen.domain.datasource.GoalDatasource
import com.goen.domain.datasource.ProcessDatasource
import com.goen.domain.datasource.ProcessHistoryDatasource
import com.goen.domain.datasource.impl.GoalDatasourceImpl
import com.goen.domain.datasource.impl.ProcessDatasourceImpl
import com.goen.domain.datasource.impl.ProcessHistoryDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ProcessHistoryDatasourceModule {
    @Provides
    fun provideProcessHistoryDatasource(datasource: ProcessHistoryDatasourceImpl): ProcessHistoryDatasource {
        return datasource
    }
}