package com.goen.domain.model.param.maki

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MakiCreateParameter (
    @Json(name = "maki_title") var makiTitle: String,
    @Json(name = "maki_key") var makiKey: String,
    @Json(name = "maki_desc") var makiDesc: String
    )