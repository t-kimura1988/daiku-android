package com.goen.domain.model.param.goal

import androidx.compose.ui.graphics.Color
import com.goen.utils.extentions.toDueDateFormat
import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

data class GoalDetailResult(
    var id: Int = 0,
    @SerializedName("create_date")
    var createDateString: String = "",
    var title: String = "",
    var accountId: Int = 0,
    var createdAccountFamilyName: String = "",
    var createdAccountGivenName: String = "",
    var createdAccountImg: String = "",
    var purpose: String = "",
    var aim: String = "",
    var dueDate: String = "",
    var archiveId: Int = 0,
    var updatingFlg: String = "",
    var archivesCreateDate: String = ""
) {

    val accountName: String get() = this.createdAccountFamilyName + " " + this.createdAccountGivenName

    val dueDateFormatter: String get() = dueDate.toDueDateFormat()

    val isUpdating: Boolean get() = updatingFlg == "1" || updatingFlg == null
}