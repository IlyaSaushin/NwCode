package com.earl.nwcode.data.mappers

import com.earl.nwcode.data.models.ImageData
import javax.inject.Inject

class BaseImageRemoteToDataMapper @Inject constructor(): ImageRemoteToDataMapper<ImageData> {

    override fun map(id: Int, standardUrl: String, preview: String, large: String) =
        ImageData.Base(id, standardUrl, preview, large)
}