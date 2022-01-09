package me.tigrao.github.repo.view

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import me.tigrao.aegis.network.ui.UiSuccess
import me.tigrao.commons.test.applyTestTheme
import me.tigrao.github.repo.R
import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.view.adapter.LayoutManagerFactory
import me.tigrao.github.repo.view.adapter.RepoAdapter
import me.tigrao.github.repo.presentation.RepoViewModel
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.ArgumentMatchers

@RunWith(AndroidJUnit4::class)
class RepoActivityTest {

    lateinit var koinApp: KoinApplication

    private val viewModel = mock<RepoViewModel>()

    @Before
    fun setup() {
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

        applyTestTheme()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun givenSuccessResponse_ShouldDisplayRecyclerView() {
        val uiStateMock = UiStateLiveData<Unit>()
        val pagedListMock = MutableLiveData<PagedList<ListItemVO>>()
        whenever(viewModel.uiState).thenReturn(uiStateMock)
        whenever(viewModel.fetchRepositories()).thenReturn(pagedListMock)

        ActivityScenario.launch(RepoActivity::class.java)
        uiStateMock.postValue(UiSuccess(Unit))

        onView(withId(R.id.rv_repo))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenInitialState_WhenLoadingRepositoryList_ShouldStartWithLoading() {
        whenever(viewModel.uiState).thenReturn(mock())
        whenever(viewModel.fetchRepositories()).thenReturn(mock())
        ActivityScenario.launch(RepoActivity::class.java)

        onView(withId(R.id.loading_repo))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenInitialState_WhenLoadingRepositoryList_ShouldStartWithListNotVisible() {
        whenever(viewModel.uiState).thenReturn(mock())
        whenever(viewModel.fetchRepositories()).thenReturn(mock())
        ActivityScenario.launch(RepoActivity::class.java)

        onView(withId(R.id.rv_repo))
            .check(matches(not(isDisplayed())))
    }

    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock<PagedList<T>>()
        whenever(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        whenever(pagedList.size).thenReturn(list.size)
        return pagedList
    }
}
