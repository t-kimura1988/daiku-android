package com.goen.utils.exception

class NotFoundException(
    override val code: Int,
    override val errorBody:String,
    override val errorCd: String
) : ApiException(code, errorBody, errorCd)