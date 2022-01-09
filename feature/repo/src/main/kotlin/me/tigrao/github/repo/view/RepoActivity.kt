package me.tigrao.github.repo.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.tigrao.github.repo.databinding.ActivityRepoBinding
import me.tigrao.github.repo.view.adapter.LayoutManagerFactory
import me.tigrao.github.repo.view.adapter.RepoAdapter
import me.tigrao.github.repo.presentation.RepoViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoActivity : AppCompatActivity() {

    private val binder by viewBinding<RepoActivity, ActivityRepoBinding> {
        ActivityRepoBinding.inflate(layoutInflater)
    }

    private val viewModel: RepoViewModel by viewModel()
    private val repoAdapter by inject<RepoAdapter>()
    private val layoutMangerFactory by inject<LayoutManagerFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binder.root)

        prepareList()

        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> onLoading()
                    is LoadState.NotLoading -> onSuccess()
                    is LoadState.Error -> onError()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.reposPager.collectLatest { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }
    }

    private fun onSuccess() {
        Toast.makeText(this, "Deu Bom Mlk : )", Toast.LENGTH_LONG).show()

        binder.loadingRepo.visibility = View.GONE
        binder.rvRepo.visibility = View.VISIBLE
    }

    private fun onLoading() {
        binder.loadingRepo.visibility = View.VISIBLE
    }

    private fun onError() {
        Toast.makeText(this, "Deu Ruim", Toast.LENGTH_LONG).show()
    }

    private fun prepareList() {
        val createLayoutManager = layoutMangerFactory.createLayoutManager(this)

        with(binder.rvRepo) {
            val dividerItemDecoration = DividerItemDecoration(
                context,
                createLayoutManager.orientation
            )
            addItemDecoration(dividerItemDecoration)
            adapter = repoAdapter
            layoutManager = createLayoutManager
        }
    }
}
