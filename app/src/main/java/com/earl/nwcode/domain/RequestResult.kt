package com.earl.nwcode.domain

sealed class RequestResult<T> {
    data class Success<T>(val data: T) : RequestResult<T>()
    data class Fail<T>(val error: Exception) : RequestResult<T>()
}