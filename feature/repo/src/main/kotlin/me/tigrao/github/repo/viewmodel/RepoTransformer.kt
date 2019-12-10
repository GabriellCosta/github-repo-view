package me.tigrao.github.repo.viewmodel

import dev.tigrao.router.Routes
import me.tigrao.aegis.network.ui.UiTransformer
import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.data.RepositoriesResponseDTO

internal class RepoTransformer(private val routes: Routes) : UiTransformer<RepositoriesResponseDTO, List<ListItemVO>> {

    override fun map(parameter: RepositoriesResponseDTO): List<ListItemVO> {
        return parameter.items.map { map ->
            ListItemVO(
                map.owner.avatarUrl,
                map.name,
                map.owner.login,
                map.stargazersCount,
                map.forksCount,
                map.description ?: "",
                routes.pullRequestRoute(map.id)
            )
        }
    }
}
