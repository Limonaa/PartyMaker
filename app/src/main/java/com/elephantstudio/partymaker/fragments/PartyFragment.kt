package com.elephantstudio.partymaker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.elephantstudio.partymaker.adapters.PeopleListAdapter
import com.elephantstudio.partymaker.data.Person
import com.elephantstudio.partymaker.databinding.FragmentPartyBinding

class PartyFragment : Fragment() {

    private var _binding: FragmentPartyBinding? = null
    private val binding get() = _binding!!
    private lateinit var peopleListAdapter: PeopleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPartyBinding.inflate(inflater, container, false)

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

        val peopleList = listOf(
            Person(
                "Dawid Wikło",
                null
            ),
            Person(
                "Dominik Kasper",
                null
            ),
            Person(
                "Marcin Musiała",
                null
            ),
            Person(
                "Marcel Świtoński",
                null
            ),
            Person(
                "Filip Czaska",
                null
            ),
        )

        binding.rvPeopleList.apply {
            peopleListAdapter = PeopleListAdapter(peopleList)
            adapter = peopleListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}