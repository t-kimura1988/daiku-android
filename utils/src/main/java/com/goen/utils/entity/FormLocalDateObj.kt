package com.goen.utils.entity

import org.threeten.bp.LocalDate

data class FormLocalDateObj (
    var date: LocalDate? = LocalDate.now(),
    var error: String? = null,
    var isError: Boolean? = true
)
