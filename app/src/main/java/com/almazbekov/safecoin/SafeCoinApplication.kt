package com.almazbekov.safecoin

import android.app.Application
import com.almazbekov.safecoin.di.BaseModules
import com.almazbekov.safecoin.di.FeatureModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class SafeCoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SafeCoinApplication)
            modules(BaseModules.modules)
        }
        loadKoinModules(FeatureModules.modules)
    }
}
