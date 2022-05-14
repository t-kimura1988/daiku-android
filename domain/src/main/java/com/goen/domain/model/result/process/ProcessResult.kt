package com.goen.domain.model.result.process

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

data class ProcessResult(
    var id: Int = 0,
    var goalId: Int = 0,
    @SerializedName("goal_create_date")
    var createDateString: String = "",
    var title: String = "",
    var familyName: String = "",
    var givenName: String = "",
    var userImage: String = "",
    var nickName: String = "",
    var priority: String = "",
    var processStatus: String = "",
    var body: String = ""
) {
    val accountName: String get() = this.familyName + " " + this.givenName

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
