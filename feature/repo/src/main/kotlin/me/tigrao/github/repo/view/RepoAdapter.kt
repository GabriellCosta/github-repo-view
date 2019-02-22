package me.tigrao.github.repo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.tigrao.github.repo.R
import me.tigrao.github.repo.data.ListItemVO

internal class RepoAdapter(private val collection: List<ListItemVO>) :
    RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_repo, parent, false)

        return RepoViewHolder(layout)
    }

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = collection[position]

        holder.title.text = item.title
        holder.description.text = item.description
        holder.stars.text = item.stars.toString()
        holder.forks.text = item.forks.toString()

        Glide.with(holder.avatar)
            .load(item.avatar)
            .into(holder.avatar)
    }

    internal class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.image_list_repo_avatar)
        val title: TextView = itemView.findViewById(R.id.txt_list_repo_title)
        val description: TextView = itemView.findViewById(R.id.txt_list_repo_description)
        val stars: TextView = itemView.findViewById(R.id.txt_list_repo_stars)
        val forks: TextView = itemView.findViewById(R.id.txt_list_repo_fork)
    }
}
