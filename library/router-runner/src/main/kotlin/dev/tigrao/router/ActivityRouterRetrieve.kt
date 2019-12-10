package dev.tigrao.router

import android.content.Intent

class ActivityRouterRetrieve {

    infix fun pullRequestRoute(intent: Intent) =
        intent.getLongExtra(KEY_PULL_REQUEST_ID, -1)

}