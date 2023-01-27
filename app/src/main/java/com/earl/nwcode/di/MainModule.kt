package com.earl.nwcode.di

import com.earl.nwcode.data.BaseRepository
import com.earl.nwcode.data.JsonParseHelper
import com.earl.nwcode.data.mappers.ImageDataToDomainMapper
import com.earl.nwcode.data.mappers.ImageRemoteToDataMapper
import com.earl.nwcode.data.models.ImageData
import com.earl.nwcode.data.retrofit.Service
import com.earl.nwcode.domain.Interactor
import com.earl.nwcode.domain.Repository
import com.earl.nwcode.domain.models.ImageDomain
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Singleton
    @Provides
    fun provideInteractor(
        repository: Repository
    ) : Interactor {
        return Interactor.Base(
            repository
        )
    }

    @Singleton
    @Provides
    fun provideRepository(
        service: Service,
        jsonParseHelper: JsonParseHelper,
        imageRemoteToDataMapper: ImageRemoteToDataMapper<ImageData>,
        imageDataToDomainMapper: ImageDataToDomainMapper<ImageDomain>
    ) : Repository {
        return BaseRepository(
            service,
            jsonParseHelper,
            imageRemoteToDataMapper,
            imageDataToDomainMapper
        )
    }

    @Singleton
    @Provides
    fun provideJsonParseHelper() : JsonParseHelper {
        return JsonParseHelper.Base()
    }
}