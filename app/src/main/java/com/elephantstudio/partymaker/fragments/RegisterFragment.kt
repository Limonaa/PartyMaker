package com.elephantstudio.partymaker.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.elephantstudio.partymaker.R
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.data.Resource
import com.elephantstudio.partymaker.databinding.FragmentLoginBinding
import com.elephantstudio.partymaker.databinding.FragmentRegisterBinding
import com.elephantstudio.partymaker.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val registerFlow = viewModel.signupFlow.value
            viewModel.signupUser(binding.etName.text.toString(), binding.etMail.text.toString(), binding.etPassword.text.toString())
            registerFlow?.let {
                when(it) {
                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), "${it.exception.message}", Toast.LENGTH_LONG).show()
                    }
                    Resource.Loading -> {
                        //TODO add progress bar
                    }
                    is Resource.Success -> {
                        val userID = viewModel.currentUser?.uid
                        val userMap = hashMapOf(
                            "name" to binding.etName.text.toString(),
                            "email" to binding.etMail.text.toString(),
                            "partyList" to listOf(
                                Party(
                                null,
                                "Grill u gawrona",
                                "18.06.2023 18:00",
                                "Opis",
                                "link/spotify",
                                false
                                ),
                                Party(
                                null,
                                "Grill u gawrona",
                                "18.06.2023 18:00",
                                "Opis",
                                "link/spotify",
                                false
                                )
                            )

                        //TODO do usuniecia "partyList" ^^^^^^
                        )
                        if (userID != null) {
                            db.collection("Users").document(userID).set(userMap)
                                .addOnCompleteListener {
                                    Toast.makeText(requireContext(), "Zarejestrowano pomyślnie", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                                }
                        }
                        findNavController().navigate(R.id.action_registerFragment_to_PartyListFragment)
                    }
                }
            }

        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

