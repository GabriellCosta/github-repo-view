package me.tigrao.github.repo.viewmodel

import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.data.RepositoriesResponseDTO

internal class RepoTransformer {

    fun map(parameter: RepositoriesResponseDTO): List<ListItemVO> {
        return parameter.items.map { map ->
            ListItemVO(
                map.owner.avatar,
                map.name,
                map.owner.login,
                map.stars,
                map.forks,
                map.description.orEmpty()
            )
        }
    }
}
