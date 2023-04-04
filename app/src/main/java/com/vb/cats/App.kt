package com.vb.cats

import android.app.Application
import com.vb.cats.di.modules.catsModule
import com.vb.cats.di.modules.databaseModule
import com.vb.cats.di.modules.favouriteCatsModule
import com.vb.cats.di.modules.networkModule
import com.vb.cats.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(networkModule, catsModule, favouriteCatsModule, databaseModule)
        }
    }
}
