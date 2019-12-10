package me.tigrao.github.pull.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.tigrao.router.ActivityRouterRetrieve
import me.tigrao.github.repo.R
import org.koin.android.ext.android.inject

class PullActivity : AppCompatActivity() {

    private val retrieveFromRoute by inject<ActivityRouterRetrieve>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pull)

        val textView = findViewById<TextView>(R.id.txt_pull_sample)

        val extra = retrieveFromRoute pullRequestRoute intent

        textView.text = "Value id: $extra"
    }
}