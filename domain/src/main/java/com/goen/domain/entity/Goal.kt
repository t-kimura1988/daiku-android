package com.goen.domain.entity

data class Goal(
    var id: Int? = null,
    var title: String? = "",
    var account: Account? = Account(),
    var purpose: String? = "",
    var aim: String? = ""
)
