package com.goen.domain.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Account(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "uid")
    var uid: String = "",
    @Json(name = "family_name")
    var familyName: String = "",
    @Json(name = "given_name")
    var givenName: String = "",
    @Json(name = "nick_name")
    var nickName: String = "",
    @Json(name = "user_image")
    var userImage: String? = ""
) {
    val accountName: String get() = "$familyName $givenName"
}
