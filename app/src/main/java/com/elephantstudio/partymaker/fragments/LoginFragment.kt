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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.elephantstudio.partymaker.databinding.FragmentLoginBinding
import com.elephantstudio.partymaker.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

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
            signIn()
        }

        binding.btnRegister.setOnClickListener {
            //TODO set register fragment
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun signIn() {
        auth.signInWithEmailAndPassword(binding.etMail.text.toString(), binding.etPassword.text.toString()).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(requireContext(), "Authentication successful", Toast.LENGTH_SHORT).show()
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUp() {
        auth.createUserWithEmailAndPassword(binding.etMail.text.toString(), binding.etPassword.text.toString()).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                // Sign in success,
                Toast.makeText(requireContext(), "Authentication successful", Toast.LENGTH_SHORT).show()
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(requireContext(), "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }
}

