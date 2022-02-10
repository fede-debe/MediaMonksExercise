package com.example.mediamonksexercise.presentation.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mediamonksexercise.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        setUpListener()
        return binding.root
    }

    private fun setUpListener() {
        binding.actionNavigateToHome.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToMainActivity()
            it.findNavController().navigate(action)
            requireActivity().finish()
        }
    }
}