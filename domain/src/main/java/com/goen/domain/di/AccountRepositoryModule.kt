package com.goen.domain.di

import com.goen.domain.repository.AccountRepository
import com.goen.domain.repository.impl.AccountRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object AccountRepositoryModule {
    @Provides
    fun provideAccountRepository(accountRepository: AccountRepositoryImpl): AccountRepository {
        return accountRepository
    }
}