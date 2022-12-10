package com.goen.domain.model.result.goal_favorite

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@JsonClass(generateAdapter = true)
data class GoalFavoriteSearchResult(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "goal_id")
    var goalId: Int = 0,
    @Json(name = "account_id")
    var accountId: Int = 0,
    @Json(name = "favorite_add_date")
    var favoriteAddDate: String = "",
    @Json(name = "goal_create_date")
    var goalCreateDate: String = "",
    @Json(name = "goal_created_account_family_name")
    var goalCreatedAccountFamilyName: String = "",
    @Json(name = "goal_created_account_given_name")
    var goalCreatedAccountGivenName: String = "",
    @Json(name = "goal_created_account_img")
    var goalCreatedAccountImg: String? = "",
    @Json(name = "favorite_add_account_family_name")
    var favoriteAddAccountFamilyName: String = "",
    @Json(name = "favorite_add_account_given_name")
    var favoriteAddAccountGivenName: String = "",
    @Json(name = "favorite_add_account_img")
    var favoriteAddAccountImg: String? = "",
    @Json(name = "title")
    var title: String = "",
    @Json(name = "purpose")
    var purpose: String = "",
) {
    val goalCreatedAccountName: String get() = this.goalCreatedAccountFamilyName + " " + this.goalCreatedAccountGivenName

    fun createDate(): String {
        if (this.goalCreateDate == "") { return ""}
        var data = LocalDate.parse(
            this.goalCreateDate,
            DateTimeFormatter.ISO_DATE
        )

        var formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")

        return data.format(formatter)
    }
}
