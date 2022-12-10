package com.goen.domain.model.result.process

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@JsonClass(generateAdapter = true)
data class ProcessResult(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "goal_id")
    var goalId: Int = 0,
    @Json(name = "goal_create_date")
    var createDateString: String = "",
    @Json(name = "title")
    var title: String = "",
    @Json(name = "family_name")
    var familyName: String = "",
    @Json(name = "given_name")
    var givenName: String = "",
    @Json(name = "user_image")
    var userImage: String = "",
    @Json(name = "nick_name")
    var nickName: String = "",
    @Json(name = "priority")
    var priority: String = "",
    @Json(name = "process_status")
    var processStatus: String = "",
    @Json(name = "body")
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
