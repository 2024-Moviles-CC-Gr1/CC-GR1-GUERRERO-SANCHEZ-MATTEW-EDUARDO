package com.example.twitter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.databinding.ItemTrendBinding

data class Trend(val title: String)

class TrendsAdapter(private val trendList: List<Trend>) : RecyclerView.Adapter<TrendsAdapter.TrendViewHolder>() {

    inner class TrendViewHolder(private val binding: ItemTrendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trend: Trend) {
            binding.trendTitle.text = trend.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val binding = ItemTrendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.bind(trendList[position])
    }

    override fun getItemCount(): Int = trendList.size
}
