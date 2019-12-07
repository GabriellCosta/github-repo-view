package me.tigrao.github.pull.api

import kotlinx.coroutines.Deferred
import me.tigrao.github.pull.data.PullRequestResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PullRequestApi {

    @GET("repos/{owner}/{repo}/pulls")
    fun fetchPullRequests(
        @Path("owner") ownerName: String,
        @Path("repo") repo: String
    ): Deferred<PullRequestResponseDTO>
}
