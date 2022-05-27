package com.goen.domain.model.result

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@JsonClass(generateAdapter = true)
data class GoalSearchResult(
    @Json(name ="id")
    var id: Int = 0,
    @Json(name = "create_date")
    var createDateString: String = "",
    @Json(name ="title")
    var title: String = "",
    @Json(name = "created_account_family_name")
    var createdAccountFamilyName: String = "",
    @Json(name = "created_account_given_name")
    var createdAccountGivenName: String = "",
    @Json(name = "created_account_img")
    var createdAccountImg: String? = "",
    @Json(name = "purpose")
    var purpose: String = "",
    @Json(name = "aim")
    var aim: String = "",
    @Json(name = "due_date")
    var dueDate: String = "",
    @Json(name = "favorite_id")
    var favoriteId: Int? = 0
) {
    val accountName: String get() = this.createdAccountFamilyName + " " + this.createdAccountGivenName

    fun createDate(): String {
        if (this.createDateString == "") { return ""}
        var data = LocalDate.parse(
            this.createDateString,
            DateTimeFormatter.ISO_DATE
        )

        var formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")

        return data.format(formatter)
    }

    fun isFavorite(): Boolean {
        if(favoriteId == null) {
            return false
        }

        if(favoriteId!! == 0) {
            return false
        }

        return true
    }
}
