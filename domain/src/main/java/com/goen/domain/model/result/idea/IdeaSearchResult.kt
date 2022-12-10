package com.goen.domain.model.result.idea

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IdeaSearchResult(
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
    @Json(name = "body")
    var body: String = "",
    @Json(name = "story_id")
    var storyId: Int? = 0,
    @Json(name = "title")
    var title: String? = "",
    @Json(name = "story_body")
    var storyBody: String? = "",
) {
    val createdAccountName: String get() = this.createdAccountFamilyName + " " + this.createdAccountGivenName

    val getIdeaTitle: String get() = if (this.title == null) "" else this.title!!

    val getStoryId: Int get() = if (this.storyId == null) 0 else this.storyId!!
}
