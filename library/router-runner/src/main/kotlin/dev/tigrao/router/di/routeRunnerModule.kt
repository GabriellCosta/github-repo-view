package dev.tigrao.router.di

import android.content.Intent
import dev.tigrao.router.ActivityRouterNavigation
import dev.tigrao.router.ActivityRouterRetrieve
import dev.tigrao.router.RouterNavigation
import dev.tigrao.router.Routes
import org.koin.dsl.module

val routerRunnerModule = module {

    single {
        Routes()
    }

    single<RouterNavigation<Intent>> {
        ActivityRouterNavigation()
    }

    single {
        ActivityRouterRetrieve()
    }
}