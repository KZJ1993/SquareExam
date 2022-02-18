package com.example.square.module.reposlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.square.databinding.ReposItemBinding
import com.example.square.model.local.ReposEntity
import com.example.square.model.relation.ReposWithBookmark
import javax.inject.Inject

class ReposAdapter @Inject constructor() :
    PagingDataAdapter<ReposWithBookmark, ReposViewHolder>(DiffUtils) {

    private var clickListener: ((ReposEntity) -> Unit)? = null

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val entity = getItem(position)
        if (entity != null) {
            holder.bind(entity, clickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        return ReposViewHolder(
            ReposItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun setClickListener(clickListener: (ReposEntity) -> Unit) {
        this.clickListener = clickListener
    }

    object DiffUtils : DiffUtil.ItemCallback<ReposWithBookmark>() {
        override fun areItemsTheSame(
            oldItem: ReposWithBookmark,
            newItem: ReposWithBookmark
        ): Boolean {
            return oldItem.repos.id == newItem.repos.id
        }

        override fun areContentsTheSame(
            oldItem: ReposWithBookmark,
            newItem: ReposWithBookmark
        ): Boolean {
            return oldItem == newItem
        }
    }
}