package dev.tigrao.router

interface RouterNavigation<RESULT> {

    fun navigate(routerOrders: RouteOrders) : RESULT

}
