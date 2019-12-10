package me.tigrao.github.repo.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import dev.tigrao.router.RouterNavigation
import me.tigrao.github.repo.R
import me.tigrao.github.repo.data.ListItemVO

internal class RepoAdapter(private val routerNavigation: RouterNavigation<Intent>) :
    PagedListAdapter<ListItemVO, RepoViewHolder>(RepoDiffConfig()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_repo, parent, false)

        return RepoViewHolder(layout, routerNavigation)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bind(item)
    }
}
