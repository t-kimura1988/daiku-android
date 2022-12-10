package com.goen.domain.di

import com.goen.domain.datasource.IdeaDatasource
import com.goen.domain.datasource.impl.IdeaDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object IdeaDatasourceModule {
    @Provides
    fun provideIdeaDatasource(repository: IdeaDatasourceImpl): IdeaDatasource {
        return repository
    }
}