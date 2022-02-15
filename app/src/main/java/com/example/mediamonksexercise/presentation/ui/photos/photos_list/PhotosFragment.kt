package com.example.mediamonksexercise.presentation.ui.photos.photos_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mediamonksexercise.databinding.FragmentPhotosBinding
import com.example.mediamonksexercise.presentation.adapter.PhotosAdapter

class PhotosFragment : Fragment() {

    private lateinit var binding: FragmentPhotosBinding
    private val viewModel: PhotosViewModel by lazy {
        ViewModelProvider(this)[PhotosViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setListeners()
        setObservers()
        return binding.root
    }

    private fun setListeners() {
        binding.rvListPhotos.adapter = PhotosAdapter(PhotosAdapter.OnClickListener { photo ->
            viewModel.openPhotoDetails(photo)
        })
    }

    private fun setObservers() {
        viewModel.navigateToPhotoDetails.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(PhotosFragmentDirections.actionPhotosFragmentToPhotoDetailsFragment(it))
                viewModel.openPhotoDetailsComplete()
            }
        }
    }
}