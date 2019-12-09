package me.tigrao.github.repo.data

import androidx.paging.PagedList

internal data class RepoVO(
    val pagedList: PagedList<ListItemVO>
)