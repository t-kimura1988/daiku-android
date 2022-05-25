package com.goen.domain.di

import com.goen.domain.BuildConfig
import com.goen.domain.interceptor.AuthorizationInterceptor
import com.goen.domain.service.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providerAccountService(retrofit: Retrofit): AccountService =
        retrofit.create(AccountService::class.java)

    @Singleton
    @Provides
    fun providerGoalService(retrofit: Retrofit): GoalService =
        retrofit.create(GoalService::class.java)

    @Singleton
    @Provides
    fun providerProcessService(retrofit: Retrofit): ProcessService =
        retrofit.create(ProcessService::class.java)

    @Singleton
    @Provides
    fun providerProcessHistoryService(retrofit: Retrofit): ProcessHistoryService =
        retrofit.create(ProcessHistoryService::class.java)

    @Singleton
    @Provides
    fun providerGoalFavoriteService(retrofit: Retrofit): GoalFavoriteService =
        retrofit.create(GoalFavoriteService::class.java)

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        val authorization = AuthorizationInterceptor()

        logging.level = if (BuildConfig.IS_DEV) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(logging)
        builder.addInterceptor(authorization)

        return builder.build()
    }

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}