package me.tigrao.github.repo.view.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import me.tigrao.github.repo.presentation.model.ListItemUiModel

internal class RepoAdapter :
    PagingDataAdapter<ListItemUiModel, RepoViewHolder>(RepoDiffConfig()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
