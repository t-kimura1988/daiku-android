package com.goen.domain.model.result.goal

import com.goen.utils.extentions.toDueDateFormat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoalDetailResult(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "create_date")
    var createDateString: String = "",
    @Json(name = "title")
    var title: String = "",
    @Json(name = "account_id")
    var accountId: Int = 0,
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
    @Json(name = "archive_id")
    var archiveId: Int? = 0,
    @Json(name = "updating_flg")
    var updatingFlg: String? = "",
    @Json(name = "archive_create_date")
    var archivesCreateDate: String = ""
) {

    val accountName: String get() = this.createdAccountFamilyName + " " + this.createdAccountGivenName

    val dueDateFormatter: String get() = dueDate.toDueDateFormat()

    val isUpdating: Boolean get() = updatingFlg == "1" || updatingFlg == null

    fun goalArchiveId(): Int {
        if(archiveId == null) return 0

        return archiveId!!
    }

    fun editable(): Boolean {
        if (updatingFlg == null) {
            return true
        }

        return updatingFlg == "1"
    }
}