package com.goen.domain.model.result.process

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@JsonClass(generateAdapter = true)
data class ProcessHistoryResult(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "create_date_string")
    var createDateString: String = "",
    @Json(name = "title")
    var title: String? = "",
    @Json(name = "family_name")
    var familyName: String = "",
    @Json(name = "given_name")
    var givenName: String = "",
    @Json(name = "user_image")
    var userImage: String = "",
    @Json(name = "nick_name")
    var nickName: String = "",
    @Json(name = "before_title")
    var beforeTitle: String? = "",
    @Json(name = "before_body")
    var beforeBody: String? = "",
    @Json(name = "before_priority")
    var beforePriority: String? = "",
    @Json(name = "priority")
    var priority: String = "",
    @Json(name = "before_process_status")
    var beforeProcessStatus: String? = "",
    @Json(name = "process_status")
    var processStatus: String = "",
    @Json(name = "comment")
    var comment: String? = "",
    @Json(name = "before_process_start_date")
    var beforeProcessStartDate: String? = "",
    @Json(name = "process_start_date")
    var processStartDate: String? = "",
    @Json(name = "before_process_end_date")
    var beforeProcessEndDate: String? = "",
    @Json(name = "process_end_date")
    var processEndDate: String? = "",
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

    val getComment: String get() = if(comment == null) "" else comment!!
}
