package com.faysal.androidmvvmp3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faysal.androidmvvmp3.R
import com.faysal.androidmvvmp3.databinding.AdapterItemBinding
import com.faysal.androidmvvmp3.models.Cat

class CatsAdapter(private val clicked: (String) -> Unit) :
    PagingDataAdapter<Cat, CatsAdapter.CatsHolder>(
        PlayersDiffCallback()
    ) {


    override fun onBindViewHolder(holder: CatsHolder, position: Int) {

        val data = getItem(position)

        holder.bind(data)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterItemBinding.inflate(layoutInflater,parent,false)
        return CatsHolder(binding)

    }

    inner class CatsHolder(
        private val binding: AdapterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Cat?) {

            binding.apply {
                name.text = data?.name
                breedId.text = data?.id.toString()
                url.text = data?.url

                this.root.setOnClickListener {
                    clicked.invoke(data?.name.toString())
                }
            }

        }
    }

    private class PlayersDiffCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }
    }

}