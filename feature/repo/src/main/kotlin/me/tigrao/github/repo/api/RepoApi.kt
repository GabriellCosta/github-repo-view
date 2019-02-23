package me.tigrao.github.repo.api

import androidx.lifecycle.LiveData
import me.tigrao.aegis.network.ui.UiState
import me.tigrao.github.repo.data.RepositoriesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RepoApi {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    fun fetchRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): LiveData<UiState<RepositoriesResponseDTO>>
}