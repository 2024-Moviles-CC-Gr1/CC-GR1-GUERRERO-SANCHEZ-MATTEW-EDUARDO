package com.example.twitter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.databinding.ItemTrendBinding

data class Trend(val title: String)

class TrendsAdapter(private val trendList: List<Trend>) : RecyclerView.Adapter<TrendsAdapter.TrendsViewHolder>() {

    class TrendsViewHolder(val binding: ItemTrendBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendsViewHolder {
        val binding = ItemTrendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendsViewHolder, position: Int) {
        val trend = trendList[position]
        holder.binding.trendTitle.text = trend.title
    }

    override fun getItemCount() = trendList.size
}
