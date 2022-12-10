package com.goen.domain.model.param.maki

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MakiDetailParameter (
    @Json(name = "maki_id") var makiId: Int
)