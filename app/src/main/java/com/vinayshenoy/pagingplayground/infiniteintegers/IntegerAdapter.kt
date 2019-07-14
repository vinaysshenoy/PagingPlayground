package com.vinayshenoy.pagingplayground.infiniteintegers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class IntegerAdapter : PagedListAdapter<Int, IntegerAdapter.ViewHolder>(DiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.render(getItem(position)!!)
  }

  public override fun getItem(position: Int): Int? {
    return super.getItem(position)
  }

  class ViewHolder(
      override val containerView: View
  ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun render(value: Int) {
      (containerView as TextView).text = value.toString()
    }
  }

  class DiffCallback : DiffUtil.ItemCallback<Int>() {

    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
      return oldItem == newItem
    }
  }
}
