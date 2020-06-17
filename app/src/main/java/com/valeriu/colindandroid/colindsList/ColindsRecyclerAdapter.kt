package com.valeriu.colindandroid.colindsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.valeriu.colindandroid.R
import com.valeriu.colindandroid.data.models.ColindDto
import kotlinx.android.synthetic.main.colind_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class ColindsRecyclerAdapter(
    private var onColindListItemClickListener: OnColindListItemClickListener,
    private var allItems: ArrayList<ColindDto>
): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private val displayedItems = ArrayList<ColindDto>()

    init {
        displayedItems.addAll(allItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ColindViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.colind_list_item,
                parent,
                false
            ),
            onColindListItemClickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ColindViewHolder -> {
                holder.bind(displayedItems[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return displayedItems.size
    }

    class ColindViewHolder constructor(itemView: View, private var onColindListItemClickListener: OnColindListItemClickListener)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        fun bind(colind: ColindDto, position: Int){
            itemView.textViewColindName.text = colind.title

            when {
                position.isEven() -> {
                    itemView.imageViewBulletPoint.setImageDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.ornament_point
                        )
                    )
                }
                position.isOdd() -> {
                    itemView.imageViewBulletPoint.setImageDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.ornament_point_red
                        )
                    )
                }
            }

            itemView.setOnClickListener(this@ColindViewHolder)
        }

        private fun Int.isEven(): Boolean {
            return this % 2 == 0
        }

        private fun Int.isOdd(): Boolean {
            return this % 2 != 0
        }

        override fun onClick(v: View?) {
            onColindListItemClickListener.onColindClick(adapterPosition)
        }
    }


    interface OnColindListItemClickListener {
        fun onColindClick(position: Int)
    }

    override fun getFilter(): Filter {
        return colindFilter
    }

    private val colindFilter: Filter = MyFilter()

    inner class MyFilter: Filter() {

        private val filterResults = FilterResults()
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            displayedItems.clear()
            if (constraint.isNullOrBlank()) {
                displayedItems.addAll(allItems)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim { it <= ' ' }
                for (item in 0..allItems.size) {
                    if (allItems[item].title.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                        displayedItems.add(allItems[item])
                    }
                }
            }
            return filterResults.also {
                it.values = displayedItems
            }
        }

        private var onNothingFound: (() -> Unit)? = null

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (displayedItems.isNullOrEmpty())
                onNothingFound?.invoke()
            notifyDataSetChanged()
        }
    }
}
