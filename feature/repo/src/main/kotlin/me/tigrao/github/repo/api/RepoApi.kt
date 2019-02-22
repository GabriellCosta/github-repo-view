package me.tigrao.github.repo.api

import androidx.lifecycle.LiveData
import me.tigrao.aegis.network.ui.UiState
import me.tigrao.github.repo.data.RepositoriesResponseDTO
import retrofit2.http.GET

internal interface RepoApi {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    fun fetchRepositories() : LiveData<UiState<RepositoriesResponseDTO>>

}