package com.goen.domain.di

import com.goen.domain.datasource.MakiDatasource
import com.goen.domain.datasource.impl.MakiDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object MakiDatasourceModule {
    @Provides
    fun provideIdeaDatasource(repository: MakiDatasourceImpl): MakiDatasource {
        return repository
    }
}