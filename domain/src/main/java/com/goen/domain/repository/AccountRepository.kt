package com.goen.domain.repository

import android.content.Context
import com.goen.domain.entity.Account
import com.goen.domain.model.param.account.AccountCreateParameter
import com.goen.utils.exception.ApiException
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun isExistAccount(
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<Account?>;

    fun createAccount(
        param: AccountCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<Account?>;

    fun updateAccount(
        param: AccountCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<Account?>

    fun getAccountInfo(
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<Account?>

    fun logout()

    fun reUpdate(
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit): Flow<Account?>

    fun deleteAccount(
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<Account?>
}