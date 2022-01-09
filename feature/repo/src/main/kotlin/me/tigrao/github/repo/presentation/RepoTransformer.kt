package me.tigrao.github.repo.presentation

import me.tigrao.github.repo.presentation.model.ListItemUiModel
import me.tigrao.github.repo.data.api.model.RepositoriesResponse

internal class RepoTransformer {

    fun map(parameter: RepositoriesResponse): List<ListItemUiModel> {
        return parameter.items.map { map ->
            ListItemUiModel(
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
