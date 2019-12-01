/*
 * Copyright 2019 Chandramohan Sudar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        return oldItem.host == newItem.host
    }

    override fun areContentsTheSame(oldItem: Website, newItem: Website): Boolean {
        return oldItem == newItem
    }
}