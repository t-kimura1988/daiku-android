package com.goen.utils.extentions

import com.goen.utils.exception.ApiException
import com.goen.utils.exception.NotFoundException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.setEvent(
    start: () -> Unit,
    error: (e: ApiException) -> Unit,
    comp: () -> Unit
): Flow<T> =
    onStart {
        start
    }.catch {
        when (it) {
            is NotFoundException -> {
                error(it)
            }

            is ApiException -> {
                error(it)
            }

            else -> {
                throw it
            }
        }
    }.onCompletion {
        comp()
    }
