package com.goen.domain.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Account(
    @field:Json(name = "id")
    var id: Int = 0,
    @field:Json(name = "uid")
    var uid: String = "",
    @field:Json(name = "family_name")
    var familyName: String = "",
    @field:Json(name = "given_name")
    var givenName: String = "",
    @field:Json(name = "nick_name")
    var nickName: String = "",
    @field:Json(name = "user_image")
    var userImage: String? = ""
) {
    val accountName: String get() = "$familyName $givenName"
}
