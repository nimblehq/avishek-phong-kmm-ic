package co.nimblehq.avishek.phong.kmmic.android

import android.app.Application
import timber.log.Timber

class KmmIcApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
