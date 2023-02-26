package com.elephantstudio.partymaker.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.elephantstudio.partymaker.R
import com.elephantstudio.partymaker.adapters.PeopleListAdapter
import com.elephantstudio.partymaker.data.Person
import com.elephantstudio.partymaker.databinding.FragmentPeopleListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleListFragment : Fragment() {

    private var _binding: FragmentPeopleListBinding? = null
    private val binding get() = _binding!!
    private lateinit var peopleListAdapter: PeopleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPeopleListBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        peopleListAdapter.setOnItemClickListener {

            showPersonInfoDialog(it)
        }

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
                "Dominik Kasper",
                null
            ),
            Person(
                "Marcin Musiała",
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
                "Dominik Kasper",
                null
            ),
            Person(
                "Marcin Musiała",
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
                "Dominik Kasper",
                null
            ),
            Person(
                "Marcin Musiała",
                null
            ),
        )

        binding.rvPeopleList.apply {
            peopleListAdapter = PeopleListAdapter(peopleList)
            adapter = peopleListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    //Dialog z informacjami o osobie
    @SuppressLint("SetTextI18n")
    private fun showPersonInfoDialog(person: Person) {
        val builder = MaterialAlertDialogBuilder(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_person_info, null)
        with(builder) {
            setTitle(person.personName)
            setPositiveButton("OK") { _, _ -> }
            setNeutralButton("Zaczep") {_, _ -> }
            setView(dialogLayout)
            dialogLayout.findViewById<TextView>(R.id.tvArrivalConfirm).text = "Potwierdzone przybycie: TAK"
            dialogLayout.findViewById<TextView>(R.id.tvPhoneNumber).text = "Nr telefonu: 604 579 593"
            show()
        }
    }
}