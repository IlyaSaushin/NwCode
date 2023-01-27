package com.earl.nwcode.di

import com.earl.nwcode.data.mappers.BaseImageDataToDomainMapper
import com.earl.nwcode.data.mappers.BaseImageRemoteToDataMapper
import com.earl.nwcode.data.mappers.ImageDataToDomainMapper
import com.earl.nwcode.data.mappers.ImageRemoteToDataMapper
import com.earl.nwcode.data.models.ImageData
import com.earl.nwcode.domain.mappers.ImageDomainToUiMapper
import com.earl.nwcode.domain.models.ImageDomain
import com.earl.nwcode.presentation.mappers.BaseImageDomainToUiMapper
import com.earl.nwcode.presentation.models.ImageUi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MappersModule {

    @Singleton
    @Provides
    fun provideImageRemoteToDataMapper() : ImageRemoteToDataMapper<ImageData> {
        return BaseImageRemoteToDataMapper()
    }

    @Singleton
    @Provides
    fun provideImageDataToDomainMapper() : ImageDataToDomainMapper<ImageDomain> {
        return BaseImageDataToDomainMapper()
    }

    @Singleton
    @Provides
    fun provideImageDomainToUiMapper() : ImageDomainToUiMapper<ImageUi> {
        return BaseImageDomainToUiMapper()
    }
}