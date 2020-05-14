package com.daniel.proiect.master

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daniel.proiect.databinding.AmiiboGridItemViewBinding
import com.daniel.proiect.network.AmiiboData

class AmiiboGridAdapter(private val clickListener: OnClickListener)
    : ListAdapter<AmiiboData, AmiiboGridAdapter.AmiiboViewHolder>(DiffCallback)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmiiboViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        return AmiiboViewHolder(AmiiboGridItemViewBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: AmiiboViewHolder, position: Int)
    {
        val amiibo = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(amiibo)
        }
        holder.bind(amiibo)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<AmiiboData>()
    {
        override fun areItemsTheSame(oldItem: AmiiboData, newItem: AmiiboData): Boolean
        {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AmiiboData, newItem: AmiiboData): Boolean
        {
            return oldItem.id == newItem.id
        }
    }

    class AmiiboViewHolder(private var binding: AmiiboGridItemViewBinding)
        : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(amiibo: AmiiboData)
        {
            binding.amiibo = amiibo
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val listener: (amiibo: AmiiboData) -> Unit)
    {
        fun onClick(amiibo: AmiiboData) = listener(amiibo)
    }
}
