package com.earl.nwcode.data.retrofit

interface NetworkService {

    sealed class EndPoints(val url: String) {
        object PixabayToken: EndPoints("33106230-b104905cd7ff74ed17e2229af")
    }
}