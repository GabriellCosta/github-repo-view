package me.tigrao.github.repo.view

import androidx.paging.PagingData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import me.tigrao.github.repo.R
import me.tigrao.github.repo.domain.model.RepositoryDataModel
import me.tigrao.github.repo.presentation.PagerProvider
import me.tigrao.github.repo.presentation.RepoViewModel
import me.tigrao.github.repo.view.adapter.LayoutManagerFactory
import me.tigrao.github.repo.view.adapter.RepoAdapter
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class RepoActivityTest {

    lateinit var koinApp: KoinApplication

    private val pagerProvider = mockk<PagerProvider>()
    private lateinit var viewModel: RepoViewModel

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun givenSuccessResponse_ShouldDisplayRecyclerView() {
        prepare()

        ActivityScenario.launch(RepoActivity::class.java)

        onView(withId(R.id.rv_repo))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenInitialState_WhenLoadingRepositoryList_ShouldStartWithLoading() {
        prepare()

        ActivityScenario.launch(RepoActivity::class.java)

        onView(withId(R.id.loading_repo))
            .check(matches(isDisplayed()))
    }

    fun prepare() {
        val list = mutableListOf<RepositoryDataModel>()

        repeat(10) {
            val item = RepositoryDataModel(
                avatar = "http://google.com.br",
                title = "mock title",
                author = "authro mock",
                31,
                3333,
                description = "mock description hahah"
            )

            list.add(item)
        }

        every { pagerProvider.providePager() } returns flowOf(PagingData.from(list))

        viewModel = RepoViewModel(pagerProvider)

        koinApp = startKoin {}

        loadKoinModules(module {
            single {
                viewModel
            }

            single {
                RepoAdapter()
            }

            single {
                LayoutManagerFactory()
            }
        })
    }
}
