package com.goen.domain.model.param.account

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountCreateParameter (
    @Json(name = "family_name") var familyName: String,
    @Json(name = "given_name") var givenName: String,
    @Json(name = "nick_name") var nickName: String)