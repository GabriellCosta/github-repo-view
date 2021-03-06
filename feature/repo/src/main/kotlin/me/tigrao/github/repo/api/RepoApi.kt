package me.tigrao.github.repo.api

import kotlinx.coroutines.Deferred
import me.tigrao.github.repo.data.RepositoriesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RepoApi {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    fun fetchRepositoriesAsync(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Deferred<RepositoriesResponseDTO>
}
