package com.example.geneticcalc.ui.stateholder.viewModels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geneticcalc.data.database.entity.RelativesEntity
import com.example.geneticcalc.databinding.ItemRecycleBinding
import com.example.geneticcalc.ui.stateholder.OnRelativesListClickListener
import com.example.geneticcalc.ui.stateholder.RelativesViewHolder


class RelativesRecyclerAdapter(
    private val onRelativesListItemListener: (Int) -> Unit
) : RecyclerView.Adapter<RelativesViewHolder>() {
    private var items: List<RelativesEntity> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelativesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecycleBinding.inflate(inflater, parent, false)
        return RelativesViewHolder(binding, onRelativesListItemListener)
    }

    override fun onBindViewHolder(holder: RelativesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<RelativesEntity>) {
        val count = itemCount
        this.items = ArrayList<RelativesEntity>(items)
        notifyItemRangeChanged(0, Math.max(count, itemCount))
    }
}