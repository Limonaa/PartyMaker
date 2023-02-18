package com.elephantstudio.partymaker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elephantstudio.partymaker.R
import com.elephantstudio.partymaker.adapters.PartyListAdapter
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.databinding.FragmentPartylistBinding
import com.elephantstudio.partymaker.viewmodels.NewPartyViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class PartyListFragment : Fragment() {

    private var _binding: FragmentPartylistBinding? = null
    private val binding get() = _binding!!
    private lateinit var partyListAdapter: PartyListAdapter
    private val newPartyViewModel: NewPartyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPartylistBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.fabAddParty.setOnClickListener {
            findNavController().navigate(R.id.newPartyFragment)
        }

        partyListAdapter.setOnItemClickListener {
            //TODO add function to viewModel
            // selected party
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() = binding.rvParties.apply{

        viewLifecycleOwner.lifecycleScope.launch {
            newPartyViewModel.partyList.collect {
                partyListAdapter = PartyListAdapter(it)
                adapter = partyListAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}