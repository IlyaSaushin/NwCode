package com.earl.nwcode.data.mappers

interface ImageRemoteToDataMapper<T> {

    fun map(
        id: Int,
        standardUrl: String,
        preview: String,
        large: String
    ) : T
}