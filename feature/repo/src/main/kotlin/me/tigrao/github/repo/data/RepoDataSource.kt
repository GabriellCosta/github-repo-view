package me.tigrao.github.repo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.tigrao.github.repo.viewmodel.RepoTransformer

internal class RepoDataSource(
    private val repository: RepoRepository,
) : PagingSource<Int, ListItemVO>() {

    private val repoTransformer = RepoTransformer()

    override fun getRefreshKey(state: PagingState<Int, ListItemVO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListItemVO> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = repository.fetchRepositories(nextPageNumber)
            val mapped = repoTransformer.map(response)


            LoadResult.Page(
                data = mapped,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(IllegalStateException("api error"))
        }
    }
}
