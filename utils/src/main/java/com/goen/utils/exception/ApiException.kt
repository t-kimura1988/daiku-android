package com.goen.utils.exception

open class ApiException(
    open val code: Int,
    open val errorBody:String,
    open val errorCd: String) : Throwable()