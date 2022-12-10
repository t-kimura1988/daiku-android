package com.goen.domain.model.result.maki

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MakiSearchResult(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "account_id")
    var accountId: Int = 0,
    @Json(name = "created_account_family_name")
    var createdAccountFamilyName: String = "",
    @Json(name = "created_account_given_name")
    var createdAccountGivenName: String = "",
    @Json(name = "created_account_img")
    var createdAccountImg: String? = "",
    @Json(name = "maki_title")
    var makiTitle: String = "",
    @Json(name = "maki_key")
    var makiKey: String = "",
    @Json(name = "maki_desc")
    var makiDesc: String = "",
) {
    val createdAccountName: String get() = this.createdAccountFamilyName + " " + this.createdAccountGivenName

}
