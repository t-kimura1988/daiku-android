package com.goen.domain.entity

data class Account(
    var id: Int = 0,
    var uid: String = "",
    var familyName: String = "",
    var givenName: String = "",
    var nickName: String = "",
    var userImage: String? = ""
) {
    val accountName: String get() = "$familyName $givenName"
}
