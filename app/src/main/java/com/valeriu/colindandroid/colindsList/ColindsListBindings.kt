package com.valeriu.colindandroid.colindsList

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.valeriu.colindandroid.data.models.ColindEntity

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<ColindEntity>?) {
    items?.let {
        (listView.adapter as ColindsListAdapter).submitList(it)
    }
}