package com.goen.domain.model.result.story

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StorySearchResult(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "idea_id")
    var ideaId: Int = 0,
    @Json(name = "account_id")
    var accountId: Int = 0,
    @Json(name = "created_account_family_name")
    var createdAccountFamilyName: String = "",
    @Json(name = "created_account_given_name")
    var createdAccountGivenName: String = "",
    @Json(name = "created_account_img")
    var createdAccountImg: String? = "",
    @Json(name = "title")
    var title: String = "",
    @Json(name = "body")
    var body: String? = "",
) {

    val storyBody: String get() = if (body == null) "" else body!!
}
