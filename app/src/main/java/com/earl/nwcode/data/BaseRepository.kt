package com.earl.nwcode.data

import com.earl.nwcode.data.mappers.ImageDataToDomainMapper
import com.earl.nwcode.data.mappers.ImageRemoteToDataMapper
import com.earl.nwcode.data.models.ImageData
import com.earl.nwcode.data.retrofit.NetworkService
import com.earl.nwcode.data.retrofit.Service
import com.earl.nwcode.domain.Repository
import com.earl.nwcode.domain.RequestResult
import com.earl.nwcode.domain.models.ImageDomain
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val service: Service,
    private val jsonParseHelper: JsonParseHelper,
    private val imageRemoteToDataMapper: ImageRemoteToDataMapper<ImageData>,
    private val imageDataToDomainMapper: ImageDataToDomainMapper<ImageDomain>
) : Repository {

    override suspend fun fetchImageForCategory(category: String): RequestResult<List<ImageDomain>> {
        return try {
            val response = service.fetchImagesForCategory(
                NetworkService.EndPoints.PixabayToken.url,
                category
            ).string()
            val imageDomain = jsonParseHelper.parseImageEntity(response)
                .map { it.mapToData(imageRemoteToDataMapper) }
                .map { it.mapToDomain(imageDataToDomainMapper) }
            if (imageDomain.isNotEmpty()) RequestResult.Success(imageDomain) else RequestResult.Fail(NullPointerException())
            RequestResult.Success(imageDomain)
        } catch (exception: Exception) {
            exception.printStackTrace()
            RequestResult.Fail(exception)
        }
    }
}