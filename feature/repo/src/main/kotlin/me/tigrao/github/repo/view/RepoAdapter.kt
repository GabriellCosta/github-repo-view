package me.tigrao.github.repo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.tigrao.github.repo.R
import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.helper.bind

internal class RepoAdapter :
    PagedListAdapter<ListItemVO, RepoAdapter.RepoViewHolder>(RepoDiffConfig()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_repo, parent, false)

        return RepoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bind(item)
    }

    internal class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar by bind<ImageView>(R.id.image_list_repo_avatar)
        private val title by bind<TextView>(R.id.txt_list_repo_title)
        private val author by bind<TextView>(R.id.txt_list_repo_author)
        private val description by bind<TextView>(R.id.txt_list_repo_description)
        private val stars by bind<TextView>(R.id.txt_list_repo_stars)
        private val forks by bind<TextView>(R.id.txt_list_repo_fork)

        fun bind(item: ListItemVO) {
            title.text = item.title
            description.text = item.description
            stars.text = item.stars.toString()
            forks.text = item.forks.toString()
            author.text = item.author

            Glide.with(avatar)
                .load(item.avatar)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_git)
                .into(avatar)
        }
    }
}
