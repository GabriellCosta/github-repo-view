package me.tigrao.github.repo.view.adapter

import androidx.recyclerview.widget.DiffUtil
import me.tigrao.github.repo.presentation.model.ListItemUiModel

internal class RepoDiffConfig : DiffUtil.ItemCallback<ListItemUiModel>() {
    override fun areItemsTheSame(oldItem: ListItemUiModel, newItem: ListItemUiModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ListItemUiModel, newItem: ListItemUiModel): Boolean {
        return oldItem == newItem
    }
}
