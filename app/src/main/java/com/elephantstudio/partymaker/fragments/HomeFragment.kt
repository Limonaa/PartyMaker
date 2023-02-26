package com.elephantstudio.partymaker.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.elephantstudio.partymaker.R
import com.elephantstudio.partymaker.adapters.PeopleListAdapter
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.data.Person
import com.elephantstudio.partymaker.databinding.FragmentHomeBinding
import com.elephantstudio.partymaker.viewmodels.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var peopleListAdapter: PeopleListAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabArrivalConfirm.setOnClickListener {
            arrivalConfirm()
        }

        binding.cvPartyDescription.setOnClickListener {
            showDescriptionDialog()
        }

        binding.cvPartyLocalisation.setOnClickListener {
            navigateToParty()
        }

        binding.cvPeopleList.setOnClickListener {

            //TODO zmiana na FragmentPeopleList
        }

        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.selectedParty.collect {
                binding.materialToolbar.title = it?.partyName
                binding.tvPartyDate.text = it?.partyDate
                binding.tvPartyDescription.text = it?.partyDescription
            }
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
        )

        binding.rvPeopleList.apply {
            peopleListAdapter = PeopleListAdapter(peopleList)
            adapter = peopleListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    @SuppressLint("PrivateResource")
    private fun arrivalConfirm() {

        //Jesli kolor fab'a jest zielony ustawia sie deafultowy i na odwrot
        if (binding.fabArrivalConfirm.backgroundTintList == AppCompatResources.getColorStateList(requireContext(), R.color.green_fab)) {
            binding.fabArrivalConfirm.backgroundTintList = AppCompatResources.getColorStateList(requireContext(), com.google.android.material.R.color.m3_sys_color_dynamic_dark_primary_container)
            Toast.makeText(requireContext(), "Odwołano przyjście", Toast.LENGTH_SHORT).show()
        } else {
            binding.fabArrivalConfirm.backgroundTintList = AppCompatResources.getColorStateList(requireContext(), R.color.green_fab)
            Toast.makeText(requireContext(), "Potwierdzono przyjście", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDescriptionDialog() {

        //Dialog z opisem imprezy
        val builder = MaterialAlertDialogBuilder(requireContext())
        with(builder) {
            setTitle("Opis")
            setMessage(binding.tvPartyDescription.text)
            setPositiveButton("OK") { _, _ -> }
            show()
        }
    }

    private fun navigateToParty() {

        //Uruchamia mapy google z podanymi wspolrzednymi
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=22.65,88.45&mode=d"))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}