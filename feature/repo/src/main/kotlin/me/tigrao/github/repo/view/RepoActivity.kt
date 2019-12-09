package me.tigrao.github.repo.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.tigrao.state.*
import me.tigrao.github.repo.R
import me.tigrao.github.repo.data.RepoVO
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

    }

    private fun prepareState() {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                StartedEvent -> onLoading()
                is SuccessEvent -> onSuccess(it)
                is ErrorEvent -> onError(it.errorDataDTO)
                FinishedEvent -> onFinish()
            }
        })
    }

    private fun onSuccess(it: SuccessEvent<RepoVO>) {
        repoAdapter.submitList(it.result.pagedList)
    }


    private fun onFinish() {
        loadingView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun onLoading() {
        loadingView.visibility = View.VISIBLE
    }

    private fun onError(errorData: ErrorDataDTO) {
        Toast.makeText(this, "Deu Ruim", Toast.LENGTH_LONG).show()
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
