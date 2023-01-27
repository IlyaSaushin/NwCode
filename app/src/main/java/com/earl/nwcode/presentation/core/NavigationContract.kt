package com.earl.nwcode.presentation.core

interface NavigationContract {

    fun splash()

    fun main()

    fun categories(category: String)

    fun selectedImage(image: String)

    fun showProgressBar()

    fun hideProgressBar()
}