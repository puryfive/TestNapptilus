package com.oriol.oompasmanager

import android.app.Application
import com.oriol.oompasmanager.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(
                viewModelModule,
                useCaseModule,
                repositoryModule,
                dataSourceModule,
                networkModule
            ))
        }
    }
}