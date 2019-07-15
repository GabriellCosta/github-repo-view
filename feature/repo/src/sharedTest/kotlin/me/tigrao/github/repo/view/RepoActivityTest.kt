package me.tigrao.github.repo.view

import android.app.Application
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.tigrao.commons.test.applyTestTheme
import me.tigrao.github.repo.R
import me.tigrao.github.repo.repoModule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin

@RunWith(AndroidJUnit4::class)
class RepoActivityTest {

    @Before
    fun setup() {
        startKoin {
            modules(repoModule)
        }

        applyTestTheme()
    }

    @Test
    fun shouldHaveRecyclerDisplayed() {

        ActivityScenario.launch(RepoActivity::class.java)

        onView(withId(R.id.rv_repo))
            .check(matches(isDisplayed()))
    }
}
