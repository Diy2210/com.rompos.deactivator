package com.rompos.deactivator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rompos.deactivator.R
import com.rompos.deactivator.Servers

class ServersAdapter(
    var items: List<Servers>,
    val clickItemCallback: ClickCallback,
    private val clickEditItemCallback: EditClickCallback,
    private val clickDeleteItemCallback: DeleteClickCallback
) : RecyclerView.Adapter<ServersAdapter.ServerViewHolder>() {

    inner class ServerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val serverTitle = view.findViewById<TextView>(R.id.serverTitle)
        private val serverUrl = view.findViewById<TextView>(R.id.serverUrl)

        fun bind(item: Servers) {
            serverTitle.text = item.title
            serverUrl.text = item.url
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) clickItemCallback.onItemClicked(items[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServerViewHolder = ServerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.server_list_item, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        holder.itemView.setOnCreateContextMenuListener { contextMenu, _, _ ->
            contextMenu.add(R.string.edit_item).setOnMenuItemClickListener {
                if (position != RecyclerView.NO_POSITION) {
                    clickEditItemCallback.onEditItemClicked(items[position])
                }
                true
            }
            contextMenu.add(R.string.delete_item).setOnMenuItemClickListener {
                if (position != RecyclerView.NO_POSITION) {
                    clickDeleteItemCallback.onDeleteItemClicked(items[position])
                }
                true
            }
        }
        holder.bind(items[position])
    }

    interface ClickCallback {
        fun onItemClicked(item: Servers)
    }

    interface EditClickCallback {
        fun onEditItemClicked(item: Servers)
    }

    interface DeleteClickCallback {
        fun onDeleteItemClicked(item: Servers)
    }
}
