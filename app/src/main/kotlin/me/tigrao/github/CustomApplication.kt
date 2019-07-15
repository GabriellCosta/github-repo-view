package me.tigrao.github

import android.app.Application
import me.tigrao.github.repo.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@CustomApplication)
            // modules
            modules(repoModule)
        }
    }
}
