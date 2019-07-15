package me.tigrao.github.repo.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import me.tigrao.aegis.network.ui.observeOnError
import me.tigrao.aegis.network.ui.observeOnLoading
import me.tigrao.aegis.network.ui.observeOnSuccess
import me.tigrao.github.repo.R
import me.tigrao.github.repo.helper.bind
import me.tigrao.github.repo.viewmodel.RepoViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoActivity : AppCompatActivity() {

    private val recyclerView by bind<RecyclerView>(R.id.rv_repo)
    private val loadingView by bind<View>(R.id.loading_repo)

    private val viewModel: RepoViewModel by viewModel()
    private val repoAdapter by inject<RepoAdapter>()
    private val layoutMangerFactory by inject<LayoutManagerFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repo)

        prepareList()
        prepareState()

        viewModel.fetchRepositories()
            .observe(this, Observer(repoAdapter::submitList))
    }

    private fun prepareState() {
        viewModel.uiState.observeOnSuccess(this) { Unit ->
            Toast.makeText(this, "Deu Bom Mlk : )", Toast.LENGTH_LONG).show()

            loadingView.visibility = View.GONE
        }
            .observeOnLoading(this) {
                loadingView.visibility = View.VISIBLE
            }
            .observeOnError(this) {
                Toast.makeText(this, "Deu Ruim", Toast.LENGTH_LONG).show()
            }
    }

    private fun prepareList() {
        val createLayoutManager = layoutMangerFactory.createLayoutManager(this)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            createLayoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = repoAdapter
        recyclerView.layoutManager = createLayoutManager
    }
}
