package com.example.twitter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.databinding.ItemTrendBinding

class TrendsAdapter(private val trendsList: List<Trend>) :
    RecyclerView.Adapter<TrendsAdapter.TrendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val binding = ItemTrendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val trend = trendsList[position]
        holder.bind(trend)
    }

    override fun getItemCount(): Int = trendsList.size

    class TrendViewHolder(private val binding: ItemTrendBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trend: Trend) {
            binding.trendTitle.text = trend.title
        }
    }
}

data class Trend(val title: String)
