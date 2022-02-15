package com.example.mediamonksexercise.presentation.ui.photos.photo_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mediamonksexercise.databinding.FragmentPhotoDetailsBinding
import com.example.mediamonksexercise.utils.setDisplayEdgeToEdge

class PhotoDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPhotoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoDetailsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setDisplayEdgeToEdge(binding.root, binding.toolbar)
    }

    private fun setListeners() {
        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
        val photo = PhotoDetailsFragmentArgs.fromBundle(requireArguments()).selectedPhoto
        binding.item = photo
    }
}
