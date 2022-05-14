package com.goen.domain.service

import com.goen.domain.entity.Account
import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.model.param.account.AccountCreateParameter
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccountService {
    @GET("api/account/show")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun getAccount(): Response<Account>

    @POST("api/account/create")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun createAccount(@Body parameter: AccountCreateParameter): Response<Account>


    @POST("api/account/update")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun updateAccount(@Body parameter: AccountCreateParameter): Response<Account>


    @POST("api/account/delete")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun deleteAccount(): Response<Account>

    @POST("api/account/re-update")
    @Headers(AuthorizationInterceptor.placeholder)
    suspend fun reUpdate(): Response<Account>
}