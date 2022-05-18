package com.goen.domain.model.result.goal

import com.google.gson.annotations.SerializedName

data class GoalArchiveSearchResult(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("goal_id")
    var goalId: Int = 0,
    @SerializedName("goal_create_date")
    var goalCreateDate: String = "",
    @SerializedName("archives_create_date")
    var archiveCreateDate: String = "",
    var publish: String = "",
    var thoughts: String = "",
    var title: String = "",
    var purpose: String = "",
    var aim: String = "",
    var dueDate: String = "",
    var familyName: String = "",
    var givenName: String = "",
    var nickName: String = "",
    var userImage: String = "",
    var processCount: Int = 0
) {
    val accountName: String get() = this.familyName + " " + this.givenName
}
