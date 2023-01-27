package com.earl.nwcode.data.models

import com.earl.nwcode.data.mappers.ImageDataToDomainMapper

interface ImageData {

    fun <T> mapToDomain(mapper: ImageDataToDomainMapper<T>) : T

    class Base(
        private val id: Int,
        private val standardUrl: String,
        private val preview: String,
        private val large: String
    ) : ImageData {
        override fun <T> mapToDomain(mapper: ImageDataToDomainMapper<T>) =
            mapper.map(id, standardUrl, preview, large)
    }
}