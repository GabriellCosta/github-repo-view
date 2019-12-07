package me.tigrao.github.repo.data

internal data class ListItemVO(
    val avatar: String,
    val title: String,
    val author: String,
    val stars: Int,
    val forks: Int,
    val description: String
)