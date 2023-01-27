package com.earl.nwcode

import android.app.Application
import com.earl.nwcode.di.DaggerApplicationComponent

class App : Application() {

    val appComponent = DaggerApplicationComponent.create()
}