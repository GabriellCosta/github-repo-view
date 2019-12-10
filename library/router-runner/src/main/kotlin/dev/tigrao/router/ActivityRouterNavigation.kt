package dev.tigrao.router

import android.content.Intent
import android.os.Parcelable

internal class ActivityRouterNavigation : RouterNavigation<Intent> {

    override fun navigate(routerOrders: RouteOrders) =
        Intent(routerOrders.where)
            .apply {
                applyExtras(routerOrders.extras)
            }


    private fun Intent.applyExtras(extras: Map<String, Any>) {
        extras.keys.forEach { key ->
            when (val value: Any? = extras[key]) {
                is Int -> putExtra(key, value)
                is Long -> putExtra(key, value)
                is String -> putExtra(key, value)
                is Parcelable -> putExtra(key, value)
                else -> error("can't apply extra $key - unknown type")
            }
        }
    }
}