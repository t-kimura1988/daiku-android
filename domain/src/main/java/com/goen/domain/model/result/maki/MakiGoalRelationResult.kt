package com.goen.domain.model.result.maki

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MakiGoalRelationResult(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "maki_id")
    var makiId: Int = 0,
    @Json(name = "goal_id")
    var goalId: Int = 0,
    @Json(name = "goal_create_date")
    var goalCreateDate: String = "",
    @Json(name = "created_by")
    var createdBy: Int? = 0,
    @Json(name = "updated_by")
    var updatedBy: Int? = 0,
    @Json(name = "created_at")
    var createdAt: String? = "",
    @Json(name = "updated_at")
    var updatedAt: String? = "",
    @Json(name = "sort_num")
    var sortNum: Int? = 0,
) {

}
