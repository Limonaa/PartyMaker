package com.elephantstudio.partymaker.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elephantstudio.partymaker.data.Article
import com.elephantstudio.partymaker.databinding.ItemShopArticleBinding

class ShoppingListAdapter(
    private var shoppingList: List<Article>
): RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    inner class ShoppingListViewHolder(val binding: ItemShopArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {

        return ShoppingListViewHolder(ItemShopArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {

        holder.binding.apply {
            tvArticleName.text = shoppingList[position].articleName
            tvArticleBuyer.text = shoppingList[position].articleBuyer
            tvArticlePrice.text = "${shoppingList[position].articlePrice}PLN"
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }
}