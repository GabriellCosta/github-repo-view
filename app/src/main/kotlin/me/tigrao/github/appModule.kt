package me.tigrao.github

import dev.tigrao.github.infra.network.NetworkBuilder
import org.koin.dsl.module

val appModule = module {
    single {
        NetworkBuilder(BuildConfig.API_URL)
    }
}
