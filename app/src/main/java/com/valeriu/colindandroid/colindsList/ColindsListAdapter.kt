package com.valeriu.colindandroid.colindsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.valeriu.colindandroid.databinding.ColindListItemBinding
import com.valeriu.colindandroid.colindsList.ColindsListAdapter.ViewHolder
import com.valeriu.colindandroid.data.models.ColindEntity

class ColindsListAdapter(private val viewModel: ColindsListViewModel) :
    ListAdapter<ColindEntity, ViewHolder>(ColindDifference()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    class ViewHolder private constructor(private val binding: ColindListItemBinding) :
            RecyclerView.ViewHolder(binding.root){

        fun bind(viewModel: ColindsListViewModel, item: ColindEntity) {
            binding.viewModel = viewModel
            binding.colind = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ColindListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}

class ColindDifference: DiffUtil.ItemCallback<ColindEntity>() {
    override fun areItemsTheSame(oldItem: ColindEntity, newItem: ColindEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ColindEntity, newItem: ColindEntity): Boolean {
        return oldItem.title == newItem.title
    }

}
