package com.goen.domain.di

import com.goen.domain.datasource.AccountDatasource
import com.goen.domain.datasource.impl.AccountDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object AccountDatasourceModule {
    @Provides
    fun provideAccountDatasource(accountDatasource: AccountDatasourceImpl): AccountDatasource {
        return accountDatasource
    }
}