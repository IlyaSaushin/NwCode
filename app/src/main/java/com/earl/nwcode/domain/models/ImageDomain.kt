package com.earl.nwcode.domain.models

import com.earl.nwcode.domain.mappers.ImageDomainToUiMapper

interface ImageDomain {

    fun <T> mapToUi(mapper: ImageDomainToUiMapper<T>) : T

    class Base(
        private val id: Int,
        private val standardUrl: String,
        private val preview: String,
        private val large: String
    ) : ImageDomain {
        override fun <T> mapToUi(mapper: ImageDomainToUiMapper<T>) =
            mapper.map(id, standardUrl, preview, large)
    }
}