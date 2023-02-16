package com.elephantstudio.partymaker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.elephantstudio.partymaker.adapters.BoughtArticlesAdapter
import com.elephantstudio.partymaker.adapters.ShoppingListAdapter
import com.elephantstudio.partymaker.data.Article
import com.elephantstudio.partymaker.data.BoughtArticle
import com.elephantstudio.partymaker.databinding.FragmentShoppingListBinding

class ShoppingListFragment: Fragment() {

    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!
    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private lateinit var boughtArticlesAdapter: BoughtArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoppingListBinding.inflate(inflater, container, false)

        setupShoppingListRV()
        setupBoughtArticlesRV()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupBoughtArticlesRV(){
        val boughtArticlesList = listOf(
            BoughtArticle(
                "Chipsy Lays",
                "Kamil Pawlaczyk",
                5,
                30
            ),
            BoughtArticle(
                "Coca-Cola",
                "Michał Stawicki",
                2,
                16

            ),
            BoughtArticle(
                "Stock 1l",
                "Michał Groch",
                1,
                59

            )
        )

        binding.rvItemsBought.apply {
            boughtArticlesAdapter = BoughtArticlesAdapter(boughtArticlesList)
            adapter = boughtArticlesAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
    }

    private fun setupShoppingListRV() {

        val shoppingList = listOf(
            Article(
                "Lays",
                "Dawid Kaszka",
                20
            ),
            Article(
                "3 Cytryny",
                "Dawid Kaszka",
                17
            ),
            Article(
                "Ciastka",
                "Dawid Kaszka",
                12
            ),
        )

        binding.rvShoppingList.apply {
            shoppingListAdapter = ShoppingListAdapter(shoppingList)
            adapter = shoppingListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}