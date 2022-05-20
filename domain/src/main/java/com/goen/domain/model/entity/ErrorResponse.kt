package com.goen.domain.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @field:Json(name = "code")
    var code: Int,

    @field:Json(name = "message")
    var message: String,

    @field:Json(name = "error_cd")
    var errorCd: String
)