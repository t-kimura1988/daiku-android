package com.goen.domain.model.param.maki

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MyMakiListSearchParameter (
    @Json(name = "page") var page: Int
)