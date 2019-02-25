package me.tigrao.github

import android.app.Application
import me.tigrao.github.repo.repoModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class CustomApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein {

        import(repoModule)

    }
}