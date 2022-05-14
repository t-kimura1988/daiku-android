package com.goen.domain.model.result

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

data class GoalSearchResult(
    var id: Int = 0,
    @SerializedName("create_date")
    var createDateString: String = "",
    var title: String = "",
    var createdAccountFamilyName: String = "",
    var createdAccountGivenName: String = "",
    var createdAccountImg: String = "",
    var purpose: String = "",
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
