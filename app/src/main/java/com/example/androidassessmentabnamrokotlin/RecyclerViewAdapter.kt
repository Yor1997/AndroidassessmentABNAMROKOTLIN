package com.example.androidassessmentabnamrokotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidassessmentabnamrokotlin.network.RepositoryData

class RecyclerViewAdapter: PagingDataAdapter<RepositoryData, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return  MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.RepoAvatar_recycler)
        val repoName: TextView = view.findViewById(R.id.repoName_recycler)
        val repoVisibility: TextView = view.findViewById(R.id.repoVisibility_recycler)
        val repoPrivate: TextView = view.findViewById(R.id.repoPrivateOrPublic_recycler)

        fun bind(data: RepositoryData) {
            repoName.text = data.name
            repoVisibility.text = data.visibility
            repoPrivate.text = if(data.private == true) { "Private" }  else { "Public"}

            Glide.with(imageView)
                .load(data.owner.avatar_url)
                .circleCrop()
                .into(imageView)
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<RepositoryData>() {
        override fun areItemsTheSame(oldItem: RepositoryData, newItem: RepositoryData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RepositoryData, newItem: RepositoryData): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.private == newItem.private
                    && oldItem.visibility == newItem.visibility
        }
    }
}