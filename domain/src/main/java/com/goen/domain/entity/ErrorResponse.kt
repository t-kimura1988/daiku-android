package com.goen.domain.entity

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    var message: String,

    var errorCd: String
)