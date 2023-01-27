package com.earl.nwcode.data.mappers

import com.earl.nwcode.domain.models.ImageDomain
import javax.inject.Inject

class BaseImageDataToDomainMapper @Inject constructor(): ImageDataToDomainMapper<ImageDomain> {

    override fun map(id: Int, standardUrl: String, preview: String, large: String) =
        ImageDomain.Base(id, standardUrl, preview, large)
}