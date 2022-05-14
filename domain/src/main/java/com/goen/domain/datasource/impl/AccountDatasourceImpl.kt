package com.goen.domain.datasource.impl

import android.util.Log
import com.goen.domain.datasource.AccountDatasource
import com.goen.domain.entity.Account
import com.goen.domain.entity.ErrorResponse
import com.goen.domain.model.param.account.AccountCreateParameter
import com.goen.domain.service.AccountService
import com.goen.utils.exception.NotFoundException
import com.google.gson.Gson
import javax.inject.Inject

class AccountDatasourceImpl @Inject constructor(
    private val accountService: AccountService
): AccountDatasource {
    override suspend fun isExistAccount(): Account? {
        var result = accountService.getAccount()

        if(result.isSuccessful) {
            return result.body()
        }


        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.string(), ErrorResponse::class.java)

        Log.println(Log.INFO, "bbbb", errRes.toString())

        throw NotFoundException(result.code(), "NotFoundAccount", errRes.errorCd)
    }

    override suspend fun createAccount(parameter: AccountCreateParameter): Account? {

        var result = accountService.createAccount(parameter = parameter)

        if(result.isSuccessful) {
            return result.body()
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.string(), ErrorResponse::class.java)

        throw NotFoundException(result.code(), "not found", errRes.errorCd)
    }

    override suspend fun updateAccount(parameter: AccountCreateParameter): Account? {
        var result = accountService.updateAccount(parameter = parameter)

        if(result.isSuccessful) {
            return result.body()
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.string(), ErrorResponse::class.java)

        throw NotFoundException(result.code(), "update account exception" , errRes.errorCd)
    }

    override suspend fun getAccount(): Account? {

        var result = accountService.getAccount()

        if(result.isSuccessful) {
            return result.body()
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.string(), ErrorResponse::class.java)

        throw NotFoundException(result.code(), "NotFoundAccount", errRes.errorCd)
    }

    override suspend fun deleteAccount(): Account? {

        var result = accountService.deleteAccount()

        if(result.isSuccessful) {
            return result.body()
        }
        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.string(), ErrorResponse::class.java)

        throw NotFoundException(result.code(), "NotFoundAccount", errRes.errorCd)
    }

    override suspend fun reUpdate(): Account? {

        var result = accountService.reUpdate()

        if(result.isSuccessful) {
            return result.body()
        }

        var errRes = Gson().fromJson<ErrorResponse>(result.errorBody()?.string(), ErrorResponse::class.java)

        throw NotFoundException(result.code(), "NotFoundAccount", errRes.errorCd)
    }
}
