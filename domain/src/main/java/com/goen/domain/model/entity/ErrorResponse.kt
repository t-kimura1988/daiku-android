package com.goen.domain.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "code")
    var code: Int,

    @Json(name = "message")
    var message: String,

    @Json(name = "error_cd")
    var errorCd: String
)