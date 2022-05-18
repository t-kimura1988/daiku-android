package com.goen.domain.model.result

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

data class GoalSearchResult(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("create_date")
    var createDateString: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("created_account_family_name")
    var createdAccountFamilyName: String = "",
    @SerializedName("created_account_given_name")
    var createdAccountGivenName: String = "",
    @SerializedName("created_account_img")
    var createdAccountImg: String = "",
    @SerializedName("purpose")
    var purpose: String = "",
    @SerializedName("aim")
    var aim: String = "",
    var gStartDate: Date? = null,
    var gEndDate: Date? = null,
    var favoriteId: Int = 0
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
}
