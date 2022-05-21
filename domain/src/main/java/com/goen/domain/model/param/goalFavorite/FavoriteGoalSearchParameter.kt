package com.goen.domain.model.param.goalFavorite

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FavoriteGoalSearchParameter (
    @Json(name = "year") var year: Int
)