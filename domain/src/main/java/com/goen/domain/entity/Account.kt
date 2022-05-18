package com.goen.domain.entity

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("uid")
    var uid: String = "",
    @SerializedName("family_name")
    var familyName: String = "",
    @SerializedName("given_name")
    var givenName: String = "",
    @SerializedName("nick_name")
    var nickName: String = "",
    @SerializedName("user_image")
    var userImage: String? = ""
) {
    val accountName: String get() = "$familyName $givenName"
}
