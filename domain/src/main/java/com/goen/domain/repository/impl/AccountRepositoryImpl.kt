package com.goen.domain.repository.impl

import com.goen.domain.datasource.AccountDatasource
import com.goen.domain.model.entity.Account
import com.goen.domain.model.param.account.AccountCreateParameter
import com.goen.domain.repository.AccountRepository
import com.goen.utils.exception.ApiException
import com.goen.utils.extentions.setEvent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDatasource: AccountDatasource
): AccountRepository {
    override fun isExistAccount(
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<Account?> {
        return flow {
            try {
                emit(accountDatasource.isExistAccount())
            } catch (e: Exception) {
                Timber.e("is account error!!! $e.message")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun createAccount(
        param: AccountCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Account?> {
        return flow {
            try {
                emit(accountDatasource.createAccount(parameter = param))
            } catch (e: Exception) {
                Timber.e("create account error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun updateAccount(
        param: AccountCreateParameter,
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Account?> {
        return flow {
            try {
                emit(accountDatasource.updateAccount(parameter = param))
            } catch (e: Exception) {
                Timber.e("update account error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun getAccountInfo(
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Account?> {
        return flow {
            try {
                emit(accountDatasource.getAccount())
            } catch (e: Exception) {
                Timber.e("get account info error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)
    }

    override fun logout() {
        Firebase.auth.signOut()
    }

    override fun reUpdate(
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) -> Unit
    ): Flow<Account?> {
        return flow {
            try {
                emit(accountDatasource.reUpdate())
            } catch (e: Exception) {
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)

    }

    override fun deleteAccount(
        onStart: () -> Unit,
        onComplate: () -> Unit,
        onError: (e: ApiException) ->Unit
    ): Flow<Account?> {
        return flow {
            try {
                emit(accountDatasource.deleteAccount())
            } catch (e: Exception) {
                Timber.e("delete account error: $e")
            }
        }.setEvent(onStart, onError, onComplate).flowOn(Dispatchers.IO)

    }
}