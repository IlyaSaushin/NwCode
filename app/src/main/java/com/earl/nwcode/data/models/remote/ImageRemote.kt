package com.earl.nwcode.data.models.remote

import com.earl.nwcode.data.mappers.ImageRemoteToDataMapper
import com.earl.nwcode.data.models.ImageData

data class ImageRemote (
    val id: Int,
    val standardUrl: String,
    val preview: String,
    val large: String
) {
    fun mapToData(mapper: ImageRemoteToDataMapper<ImageData>) =
        mapper.map(id, standardUrl, preview, large)
}