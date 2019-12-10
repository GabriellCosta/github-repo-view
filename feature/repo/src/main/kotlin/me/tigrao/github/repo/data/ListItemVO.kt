package me.tigrao.github.repo.data

import dev.tigrao.router.RouteOrders

internal data class ListItemVO(
    val avatar: String,
    val title: String,
    val author: String,
    val stars: Int,
    val forks: Int,
    val description: String,
    val destination: RouteOrders
)
