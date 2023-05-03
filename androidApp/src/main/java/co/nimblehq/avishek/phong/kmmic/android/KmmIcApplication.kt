package co.nimblehq.avishek.phong.kmmic.android

import android.app.Application
import co.nimblehq.avishek.phong.kmmic.di.initKoin
import org.koin.android.ext.koin.androidContext
import timber.log.Timber

class KmmIcApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        initKoin {
            androidContext(this@KmmIcApplication)
        }
    }
}
