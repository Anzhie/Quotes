package com.khasanova.quotes

import android.app.Application
import com.khasanova.quotes.di.AppComponent
import com.khasanova.quotes.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create()

        super.onCreate()
    }
}