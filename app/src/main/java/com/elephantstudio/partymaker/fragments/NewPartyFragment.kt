package com.elephantstudio.partymaker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.elephantstudio.partymaker.R
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.databinding.FragmentNewPartyBinding
import com.elephantstudio.partymaker.viewmodels.MainViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NewPartyFragment : Fragment() {

    private var _binding: FragmentNewPartyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    private var dataBase = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewPartyBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tiPartyDate.setOnClickListener{
                showCalendar()
        }

        binding.fabAddParty.setOnClickListener {

            checkPartyRequirements()
//            val partyMap = hashMapOf(
//                "partyName" to binding.tiPartyName.text.toString(),
//                "partyDate" to binding.tiPartyDate.text.toString(),
//            )
//
//                dataBase.collection("parties").document("Impreza 1").set(partyMap)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showCalendar() {

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        datePicker.show(parentFragmentManager, "PartyDate")

        datePicker.addOnPositiveButtonClickListener {
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
            val date = dateFormatter.format(Date(it))
            binding.tiPartyDate.setText(date)
        }
    }

    private fun checkPartyRequirements(){

        if (binding.tiPartyName.text.isNullOrEmpty() || binding.tiPartyDate.text.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Wypełnij wymagane pola", Toast.LENGTH_LONG).show()
        } else {
            viewModel.addParty(Party(
                null,
                partyName = binding.tiPartyName.text.toString(),
                partyDate = binding.tiPartyDate.text.toString()
            ))
            findNavController().navigate(R.id.PartyListFragment)
        }
    }


}