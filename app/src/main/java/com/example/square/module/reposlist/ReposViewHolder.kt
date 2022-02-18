package com.example.square.module.reposlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.square.databinding.ReposItemBinding
import com.example.square.model.local.ReposEntity
import com.example.square.model.relation.ReposWithBookmark

class ReposViewHolder(private val binding: ReposItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(entity: ReposWithBookmark, clickListener: ((ReposEntity) -> Unit)?) {
        binding.apply {
            data = entity.repos
            imgBookmark.visibility = if (entity.bookmark != null) View.VISIBLE else View.GONE
        }
        itemView.setOnClickListener {
            clickListener?.let { listener -> listener(entity.repos) }
        }
    }
}