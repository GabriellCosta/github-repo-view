package me.tigrao.github.repo.api

import io.reactivex.Observable
import me.tigrao.github.repo.data.RepositoriesResponseDTO

internal interface RepoRepository {

    fun fetchRepositories(page: Int): Observable<RepositoriesResponseDTO>
}
