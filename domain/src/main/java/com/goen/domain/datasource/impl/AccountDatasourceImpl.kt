package com.goen.domain.datasource.impl

import android.util.Log
import com.goen.domain.datasource.AccountDatasource
import com.goen.domain.model.entity.Account
import com.goen.domain.model.entity.ErrorResponse
import com.goen.domain.model.param.account.AccountCreateParameter
import com.goen.domain.service.AccountService
import com.goen.utils.exception.NotFoundException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject


class AccountDatasourceImpl @Inject constructor(
    private val accountService: AccountService
): AccountDatasource {

    var moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    override suspend fun isExistAccount(): Account? {
        Log.i("AccountAAAAAAA", "this is wrong....")
        var result = accountService.getAccount()

        Log.i("AccountDatasource0", result.body().toString())
        if(result.isSuccessful) {
            Log.i("AccountDatasource1", result.body().toString())
            return result.body()
        }
        Log.i("AccountDatasource2", result.body().toString())

        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        Log.println(Log.INFO, "error", errRes.toString())

        throw NotFoundException(result.code(), "NotFoundAccount", errRes!!.errorCd)
    }

    override suspend fun createAccount(parameter: AccountCreateParameter): Account? {

        var result = accountService.createAccount(parameter = parameter)

        if(result.isSuccessful) {
            return result.body()
        }

        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw NotFoundException(result.code(), "not found", errRes!!.errorCd)
    }

    override suspend fun updateAccount(parameter: AccountCreateParameter): Account? {
        var result = accountService.updateAccount(parameter = parameter)

        if(result.isSuccessful) {
            return result.body()
        }

        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw NotFoundException(result.code(), "update account exception" , errRes!!.errorCd)
    }

    override suspend fun getAccount(): Account? {

        var result = accountService.getAccount()

        if(result.isSuccessful) {
            return result.body()
        }

        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw NotFoundException(result.code(), "NotFoundAccount", errRes!!.errorCd)
    }

    override suspend fun deleteAccount(): Account? {

        var result = accountService.deleteAccount()

        if(result.isSuccessful) {
            return result.body()
        }

        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw NotFoundException(result.code(), "NotFoundAccount", errRes!!.errorCd)
    }

    override suspend fun reUpdate(): Account? {

        var result = accountService.reUpdate()

        if(result.isSuccessful) {
            return result.body()
        }


        var jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
        var errRes = jsonAdapter.fromJson(result.errorBody()?.string())

        throw NotFoundException(result.code(), "NotFoundAccount", errRes!!.errorCd)
    }
}
