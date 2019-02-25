package me.tigrao.github.repo.view

import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.view.View
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
import me.tigrao.github.repo.helper.bind
import me.tigrao.github.repo.viewmodel.RepoViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RepoActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val recyclerView by bind<RecyclerView>(R.id.rv_repo)
    private val loadingView by bind<View>(R.id.loading_repo)

    private val viewModel: RepoViewModel by viewModel()
    private val repoAdapter by instance<RepoAdapter>()

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

    private val linearLayoutManager = LinearLayoutManager(this)

    private fun prepareList() {
        recyclerView.addItemDecoration(CustomItemDecoration())
        recyclerView.adapter = repoAdapter
        recyclerView.layoutManager = linearLayoutManager
    }
}
