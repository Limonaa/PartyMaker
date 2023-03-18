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
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.elephantstudio.partymaker.R
import com.elephantstudio.partymaker.data.Resource
import com.elephantstudio.partymaker.databinding.FragmentLoginBinding
import com.elephantstudio.partymaker.viewmodels.MainViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import java.lang.reflect.Modifier

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val loginFlow = viewModel.loginFlow.value
            viewModel.loginUser(binding.etMail.text.toString(), binding.etPassword.text.toString())
            lifecycleScope.launchWhenStarted() {
                loginFlow?.let {
                    when (it) {
                        is Resource.Failure -> {
                            Toast.makeText(requireContext(), "${it.exception.message}", Toast.LENGTH_LONG).show()
                        }
                        Resource.Loading -> {
                            //TODO add progress bar
                        }
                        is Resource.Success -> {
                            Toast.makeText(requireContext(), "Zalogowano pomyślnie", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_loginFragment_to_PartyListFragment)
                        }

                    }
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

