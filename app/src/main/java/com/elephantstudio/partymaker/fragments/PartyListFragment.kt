package com.elephantstudio.partymaker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.elephantstudio.partymaker.adapters.PartyListAdapter
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.databinding.FragmentPartylistBinding


class PartyListFragment : Fragment() {

    private var _binding: FragmentPartylistBinding? = null
    private val binding get() = _binding!!
    private lateinit var partyListAdapter: PartyListAdapter

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


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {

        val partyList = listOf(
            Party(
                "Impreza u Czara",
                "20/05/2023   18:00"
            ),
            Party(
                "Impreza u Dawida",
                "17/11/2023   19:00"
            )
        )

        binding.rvParties.apply {
            partyListAdapter = PartyListAdapter(partyList)
            adapter = partyListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}