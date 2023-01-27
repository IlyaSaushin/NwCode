package com.earl.nwcode.domain.mappers

interface ImageDomainToUiMapper<T> {

    fun map(
        id: Int,
        standardUrl: String,
        preview: String,
        large: String
    ) : T
}