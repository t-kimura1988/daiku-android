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
    var favoriteId: Int? = 0,
    @Json(name = "updating_flg")
    var updatingFlg: String? = "",
    @Json(name = "archive_id")
    var archiveId: Int? = 0,
    @Json(name = "archive_create_date")
    var archivesCreateDate: String? = "",
    @Json(name = "maki_relation_id")
    var makiRelationId: Int? = 0,
    @Json(name = "sort_num")
    var sortNum: Int? = 0,
    @Json(name = "maki_key")
    var makiKey: String? = ""
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

    fun dueDate(): String {
        if (this.dueDate == "") { return ""}
        var data = LocalDate.parse(
            this.dueDate,
            DateTimeFormatter.ISO_DATE
        )

        var formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")

        return data.format(formatter)
    }

    fun makiKeySortNum(): String {
        if (makiKey == null || sortNum == null) {
            return ""
        }

        return makiKey + " "+ sortNum!!.toString() + "巻"
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

    fun isArchive(): Boolean {
        if(archiveId == null) {
            return false
        }

        if(archiveId!! == 0) {
            return false
        }
        return true
    }

    fun isExistMakiRelation(): Boolean {
        if (makiRelationId == null) {
            return false
        }

        return true
    }
}
