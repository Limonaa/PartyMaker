package com.elephantstudio.partymaker.fragments

import android.app.ProgressDialog.show
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elephantstudio.partymaker.R
import com.elephantstudio.partymaker.databinding.FragmentHomeBinding
import com.elephantstudio.partymaker.databinding.FragmentNewPartyBinding
import com.google.android.material.datepicker.MaterialDatePicker


class NewPartyFragment : Fragment() {

    private var _binding: FragmentNewPartyBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewPartyBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWhen.setOnClickListener{
                val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Kiedy odbędzie się impreza?")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
                datePicker.show(parentFragmentManager, "PartyDate")
        }

    }
}