package com.earl.nwcode.domain

import com.earl.nwcode.domain.models.ImageDomain

interface Interactor {

    suspend fun fetchImageForCategory(category: String) : RequestResult<List<ImageDomain>>

    class Base(
        private val repository: Repository
    ) : Interactor {

        override suspend fun fetchImageForCategory(category: String) =
            repository.fetchImageForCategory(category)
    }
}