package dev.tigrao.router

const val KEY_PULL_REQUEST_ID = "_KEY_PULL_REQUEST_ID"

class Routes {

    fun pullRequestRoute(id: Long) =
        RouteOrders(
            "me.tigrao.github.pull.ui.PullActivity",
            mapOf(KEY_PULL_REQUEST_ID to id)
        )
}