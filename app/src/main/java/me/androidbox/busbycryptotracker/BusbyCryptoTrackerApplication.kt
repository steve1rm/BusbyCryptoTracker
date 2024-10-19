package me.androidbox.busbycryptotracker

import android.app.Application
import me.androidbox.core.data.di.networkModule
import me.androidbox.crypto.data.di.dataSourceModule
import me.androidbox.crypto.presentation.di.coinListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BusbyCryptoTrackerApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidContext(this@BusbyCryptoTrackerApplication)
            androidLogger()

            modules(
                networkModule,
                dataSourceModule,
                coinListModule
            )
        }
    }
}