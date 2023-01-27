package com.earl.nwcode.domain

import com.earl.nwcode.domain.models.ImageDomain

interface Repository {

    suspend fun fetchImageForCategory(category: String) : RequestResult<List<ImageDomain>>
}