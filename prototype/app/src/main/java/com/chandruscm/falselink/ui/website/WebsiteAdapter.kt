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

import android.os.Bundle
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.databinding.ListItemWebsiteBinding
import com.chandruscm.falselink.utils.compatRemoveIf
import com.chandruscm.falselink.ui.website.WebsitesViewHolder.WebsiteItemHolder

class WebsiteAdapter(
    private val websiteActionsHandler: WebsiteActionsHandler,
    savedState: Bundle?
) : ListAdapter<Website, WebsitesViewHolder>(DiffCallback) {

    companion object {
        private const val STATE_KEY_EXPANDED_IDS = "WebsiteAdapter:expandedIds"
    }

    private var expandedIds = mutableSetOf<String>()

    init {
        savedState?.getStringArray(STATE_KEY_EXPANDED_IDS)?.let {
            expandedIds.addAll(it)
        }
    }

    fun onSaveInstanceState(state: Bundle) {
        state.putStringArray(STATE_KEY_EXPANDED_IDS, expandedIds.toTypedArray())
    }

    override fun submitList(list: List<Website>?) {
        // Clear out any invalid IDs
        if (list == null) {
            expandedIds.clear()
        } else {
            val ids = list.filterIsInstance<Website>().map { it.host }
            expandedIds.compatRemoveIf { it !in ids }
        }
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebsitesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WebsiteItemHolder(
            ListItemWebsiteBinding.inflate(inflater, parent, false).apply {
                actionHandler = websiteActionsHandler
            }
        )
    }

    override fun onBindViewHolder(holder: WebsitesViewHolder, position: Int) {
        if (holder is WebsiteItemHolder) {
            bindWebsiteItemHolder(holder, getItem(position))
        }
    }

    private fun bindWebsiteItemHolder(holder: WebsiteItemHolder, item: Website) {
        holder.binding.apply {
            website = item
            isExpanded = expandedIds.contains(item.host)
        }
        holder.itemView.setOnClickListener {
            val parent = holder.itemView.parent as? ViewGroup ?: return@setOnClickListener
            val expanded = holder.binding.isExpanded ?: false
            if (expanded) {
                expandedIds.remove(item.host)
            } else {
                expandedIds.add(item.host)
            }
            val transition = TransitionInflater
                                .from(parent.context)
                                .inflateTransition(R.transition.list_item_expand)
            TransitionManager.beginDelayedTransition(parent, transition)
            holder.binding.apply {
                isExpanded = !expanded
            }
        }
    }
}

sealed class WebsitesViewHolder(itemView: View) : ViewHolder(itemView) {

    class WebsiteItemHolder(
        val binding: ListItemWebsiteBinding
    ) : WebsitesViewHolder(binding.root)
}

object DiffCallback : DiffUtil.ItemCallback<Website>() {

    override fun areItemsTheSame(oldItem: Website, newItem: Website): Boolean {
        return oldItem.host == newItem.host
    }

    override fun areContentsTheSame(oldItem: Website, newItem: Website): Boolean {
        return oldItem == newItem
    }
}