package com.rompos.deactivator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rompos.deactivator.databinding.PluginListItemBinding
import com.rompos.deactivator.models.PluginViewModel

class PluginsAdapter(
    val context: Context,
    var items: List<com.rompos.deactivator.mpp.models.PluginViewModel>
) : RecyclerView.Adapter<PluginsAdapter.ItemTableViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemTableViewHolder {
        return ItemTableViewHolder(PluginListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemTableViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ItemTableViewHolder(private val binding: PluginListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PluginViewModel) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}