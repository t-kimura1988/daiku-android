package com.goen.domain.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Goal(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "title")
    var title: String? = "",
    @Json(name = "account")
    var account: Account? = Account(),
    @Json(name = "purpose")
    var purpose: String? = "",
    @Json(name = "aim")
    var aim: String? = ""
)
