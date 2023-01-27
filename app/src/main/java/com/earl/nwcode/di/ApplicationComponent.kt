package com.earl.nwcode.di

import com.earl.nwcode.presentation.screens.categories.FragmentCategoryImage
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, NetworkModule::class, MappersModule::class])
interface ApplicationComponent {

    fun inject(fragment: FragmentCategoryImage)
}