package com.elephantstudio.partymaker.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elephantstudio.partymaker.data.BoughtArticle
import com.elephantstudio.partymaker.databinding.ItemBoughtArticleBinding
import com.elephantstudio.partymaker.databinding.ItemShopArticleBinding

class BoughtArticlesAdapter(
    private val boughtArticlesList: List<BoughtArticle>
    ): RecyclerView.Adapter<BoughtArticlesAdapter.BoughtArticlesViewHolder>() {
        inner class BoughtArticlesViewHolder(val binding: ItemBoughtArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoughtArticlesAdapter.BoughtArticlesViewHolder {
        return BoughtArticlesViewHolder(ItemBoughtArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }


    override fun onBindViewHolder(holder: BoughtArticlesAdapter.BoughtArticlesViewHolder, position: Int) {
        holder.binding.apply {
            tvBoughtArticleName.text = boughtArticlesList[position].boughtArticleName
            tvBoughtArticleCount.text = "ilość: ${boughtArticlesList[position].boughtArticleCount.toString()}"
            tvBoughtArticlePrice.text = "${boughtArticlesList[position].boughtArticlePrice.toString()} PLN"

        }

    }

    override fun getItemCount(): Int {
        return boughtArticlesList.size
    }
}