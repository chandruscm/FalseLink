package com.chandruscm.falselink.ui.website

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.databinding.ListItemWebsiteBinding

class WebsiteAdapter : ListAdapter<Website, WebsiteAdapter.ViewHolder>(
    DiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val website = getItem(position)
        holder.bind(createOnClickListener(), website)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemWebsiteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
//            Toast.makeText(it.context, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(
        private val binding: ListItemWebsiteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Website) {
            binding.apply {
                clickListener = listener
                website = item
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Website>() {

    override fun areItemsTheSame(oldItem: Website, newItem: Website): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Website, newItem: Website): Boolean {
        return oldItem.url == newItem.url
    }
}