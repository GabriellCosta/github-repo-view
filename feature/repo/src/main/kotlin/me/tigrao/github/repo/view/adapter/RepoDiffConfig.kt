package me.tigrao.github.repo.view.adapter

import androidx.recyclerview.widget.DiffUtil
import me.tigrao.github.repo.data.ListItemVO

internal class RepoDiffConfig : DiffUtil.ItemCallback<ListItemVO>() {
    override fun areItemsTheSame(oldItem: ListItemVO, newItem: ListItemVO): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ListItemVO, newItem: ListItemVO): Boolean {
        return oldItem == newItem
    }
}
