package com.goen.domain.model.result.goal

import com.goen.domain.enums.GoalArchivePublishedLevel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoalArchiveSearchResult(
    @Json( name ="id")
    var id: Int = 0,
    @Json( name ="goal_id")
    var goalId: Int = 0,
    @Json(name ="goal_create_date")
    var goalCreateDate: String = "",
    @Json(name = "archives_create_date")
    var archiveCreateDate: String = "",
    @Json(name = "publish")
    var publish: String = "",
    @Json(name = "thoughts")
    var thoughts: String = "",
    @Json(name = "title")
    var title: String = "",
    @Json(name = "purpose")
    var purpose: String = "",
    @Json(name = "aim")
    var aim: String = "",
    @Json(name = "due_date")
    var dueDate: String = "",
    @Json(name = "family_name")
    var familyName: String = "",
    @Json(name = "given_name")
    var givenName: String = "",
    @Json(name = "nick_name")
    var nickName: String = "",
    @Json(name = "user_image")
    var userImage: String? = "",
    @Json(name = "process_count")
    var processCount: Int = 0
) {
    val accountName: String get() = this.familyName + " " + this.givenName

    val publishedLevel: GoalArchivePublishedLevel get() = GoalArchivePublishedLevel.of(publish)
}
