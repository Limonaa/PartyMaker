package com.elephantstudio.partymaker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.elephantstudio.partymaker.adapters.BoughtArticlesAdapter
import com.elephantstudio.partymaker.adapters.ShoppingListAdapter
import com.elephantstudio.partymaker.data.Article
import com.elephantstudio.partymaker.data.BoughtArticle
import com.elephantstudio.partymaker.databinding.FragmentShoppingListBinding
import com.elephantstudio.partymaker.viewmodels.MainViewModel
import com.elephantstudio.partymaker.viewmodels.ShoppingListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShoppingListFragment: Fragment() {

    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!
    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private lateinit var boughtArticlesAdapter: BoughtArticlesAdapter
    private val viewModel: ShoppingListViewModel by activityViewModels()

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

        setupShoppingListRV()

        binding.btnAddProduct.setOnClickListener{
            viewModel.addArticle(
                Article(null,
                    articleName = binding.tiAddItem.text.toString()
            )
            )
        }


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


        binding.rvShoppingList.apply {
            viewLifecycleOwner.lifecycleScope.launch{
                viewModel.articles.collect{
                    shoppingListAdapter = ShoppingListAdapter(it)
                    adapter = shoppingListAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }

        }
    }
}