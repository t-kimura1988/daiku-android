package com.goen.domain.model.result.story_character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryCharacterSearchResult(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "idea_id")
    var idea_id: Int = 0,
    @Json(name = "story_id")
    var storyId: Int? = 0,
    @Json(name = "chara_name")
    var charaName: String = "",
    @Json(name = "chara_desc")
    var charaDesc: String = "",
    @Json(name = "leader_flg")
    var leaderFlg: String = "",
) {
    fun getCharaInfo(): String {
        var leader: String = if (leaderFlg == "1") "(主役)" else ""
        return "$charaName $leader"
    }
}
