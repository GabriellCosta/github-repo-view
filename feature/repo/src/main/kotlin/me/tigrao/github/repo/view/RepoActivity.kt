package me.tigrao.github.repo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.tigrao.aegis.network.ui.observeOnSuccess
import me.tigrao.github.repo.R
import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.viewmodel.RepoViewModel

class RepoActivity : AppCompatActivity() {

    private val viewModel: RepoViewModel = RepoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repo)

        prepareState()
        viewModel.fetchRepositories()
    }

    private fun prepareState() {
        viewModel.uiState
            .observeOnSuccess(this, ::onSuccess)
    }

    private fun onSuccess(collection: List<ListItemVO>) {
        val rv = findViewById<RecyclerView>(R.id.rv_repo)
        rv.adapter = RepoAdapter(collection)
        rv.layoutManager = LinearLayoutManager(this)
    }
}