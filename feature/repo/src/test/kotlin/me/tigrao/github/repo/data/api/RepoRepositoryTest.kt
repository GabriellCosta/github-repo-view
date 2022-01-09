package me.tigrao.github.repo.data.api

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

@Suppress("DeferredResultUnused")
internal class RepoRepositoryTest {

    val api = mock<RepoApi>()

    val repository: RepoRepository = RepoRepositoryImpl(api)

    @Test
    fun givenARepositorySearch_WhenSuccess_ShouldDoItSortedByStars() {
        repository.fetchRepositories(1)

        verify(api).fetchRepositoriesAsync(any(), eq("stars"), any())
    }

    @Test
    fun givenARepositorySearch_whenSuccess_ShouldDoItBySearchKotlinLanguage() {
        repository.fetchRepositories(1)

        verify(api).fetchRepositoriesAsync(eq("language:kotlin"), any(), any())
    }

    @Test
    fun givenARepositorySearch_whenSuccess_ShouldDoItBySearchTheRequestedPage() {
        val targetPage = 3
        repository.fetchRepositories(targetPage)

        verify(api).fetchRepositoriesAsync(any(), any(), eq(targetPage))
    }
}
