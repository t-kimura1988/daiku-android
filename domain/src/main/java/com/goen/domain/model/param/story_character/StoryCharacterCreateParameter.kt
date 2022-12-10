package com.goen.domain.model.param.story_character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryCharacterCreateParameter (
    @Json(name = "story_id") var storyId: Int,
    @Json(name = "idea_id") var ideaId: Int,
    @Json(name = "chara_name") var charaName: String,
    @Json(name = "chara_desc") var charaDesc: String,
    @Json(name = "leader_flg") var leaderFlg: Int

    )