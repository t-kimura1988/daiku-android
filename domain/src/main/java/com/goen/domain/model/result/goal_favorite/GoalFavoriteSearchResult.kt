package com.goen.domain.model.result.goal_favorite

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

data class GoalFavoriteSearchResult(
    var id: Int = 0,
    var goalId: Int = 0,
    var accountId: Int = 0,
    var favoriteAddDate: String = "",
    var goalCreateDate: String = "",
    var goalCreatedAccountFamilyName: String = "",
    var goalCreatedAccountGivenName: String = "",
    var goalCreatedAccountImg: String = "",
    var favoriteAddAccountFamilyName: String = "",
    var favoriteAddAccountGivenName: String = "",
    var favoriteAddAccountImg: String = "",
    var title: String = "",
    var purpose: String = "",
    var gStartDate: Date? = null,
    var gEndDate: Date? = null
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
