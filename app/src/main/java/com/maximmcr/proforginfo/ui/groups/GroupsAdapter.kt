package com.maximmcr.proforginfo.ui.groups

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maximmcr.proforginfo.R
import com.maximmcr.proforginfo.data.foreign.model.Group
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_groups.*

class GroupsAdapter constructor(
        private val list: MutableList<Group> = mutableListOf(),
        private val clickListener: (Group) -> Unit
): RecyclerView.Adapter<GroupsAdapter.GroupHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupHolder =
            GroupHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_groups, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    fun add(group: Group) {
        list.add(0, group)
        notifyItemInserted(0)
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addAll(groups: List<Group>) {
        list.addAll(0, groups)
//        notifyItemRangeInserted(0, groups.size)
        notifyDataSetChanged()
    }

    class GroupHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer  {
        fun bind(group: Group, listener: (Group) -> Unit) {
            group_name.text = group.name
            group_container.setOnClickListener { listener(group) }
        }
    }

}