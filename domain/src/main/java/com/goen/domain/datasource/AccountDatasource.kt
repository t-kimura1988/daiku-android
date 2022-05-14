package com.goen.domain.datasource

import com.goen.domain.entity.Account
import com.goen.domain.model.param.account.AccountCreateParameter

interface AccountDatasource {
    suspend fun isExistAccount(): Account?;
    suspend fun createAccount(parameter: AccountCreateParameter): Account?;
    suspend fun updateAccount(parameter: AccountCreateParameter): Account?;
    suspend fun getAccount(): Account?
    suspend fun deleteAccount(): Account?;
    suspend fun reUpdate(): Account?;
}