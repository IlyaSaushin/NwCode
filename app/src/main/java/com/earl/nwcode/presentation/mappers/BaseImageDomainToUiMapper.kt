package com.earl.nwcode.presentation.mappers

import com.earl.nwcode.domain.mappers.ImageDomainToUiMapper
import com.earl.nwcode.presentation.models.ImageUi
import javax.inject.Inject

class BaseImageDomainToUiMapper@Inject constructor() : ImageDomainToUiMapper<ImageUi> {

    override fun map(id: Int, standardUrl: String, preview: String, large: String) =
        ImageUi.Base(id, standardUrl, preview, large)
}