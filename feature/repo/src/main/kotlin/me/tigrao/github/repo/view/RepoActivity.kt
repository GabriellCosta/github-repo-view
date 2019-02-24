package me.tigrao.github.repo.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.tigrao.aegis.network.ui.observeOnError
import me.tigrao.aegis.network.ui.observeOnLoading
import me.tigrao.aegis.network.ui.observeOnSuccess
import me.tigrao.github.repo.R
import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.viewmodel.RepoViewModel

class RepoActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView = findViewById(R.id.rv_repo)

    private val viewModel: RepoViewModel = RepoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repo)

        prepareState()
        viewModel.fetchRepositories().observe(this, Observer {
            onSuccess(it)
        })
    }

    private fun prepareState() {
        viewModel.uiState.observeOnSuccess(this) { Unit ->
            Toast.makeText(this, "Deu Bom Mlk : )", Toast.LENGTH_LONG).show()
        }
            .observeOnLoading(this) {
                Toast.makeText(this, "Loading....", Toast.LENGTH_LONG).show()
            }
            .observeOnError(this) {
                Toast.makeText(this, "Deu Ruim", Toast.LENGTH_LONG).show()
            }
    }

    private fun onSuccess(collection: PagedList<ListItemVO>) {
        val repoAdapter = RepoAdapter()
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(CustomItemDecoration())
        recyclerView.adapter = repoAdapter
        repoAdapter.submitList(collection)
        recyclerView.layoutManager = linearLayoutManager
    }
}
