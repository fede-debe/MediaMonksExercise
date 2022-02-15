package com.example.mediamonksexercise.presentation.ui.photos.photos_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mediamonksexercise.MyApplication
import com.example.mediamonksexercise.R
import com.example.mediamonksexercise.databinding.FragmentPhotosBinding
import com.example.mediamonksexercise.presentation.adapter.PhotosAdapter
import com.example.mediamonksexercise.utils.GridItemDecorator
import com.example.mediamonksexercise.utils.setDisplayEdgeToEdge
import javax.inject.Inject

class PhotosFragment : Fragment() {

    private lateinit var binding: FragmentPhotosBinding

    @Inject
    lateinit var viewModel: PhotosViewModel
    private val adapter = PhotosAdapter(PhotosAdapter.OnClickListener { photo ->
        viewModel.openPhotoDetails(photo)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        (requireContext().applicationContext as MyApplication).appComponent.injectPhotos(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setObservers()
        setDisplayEdgeToEdge(binding.root, binding.toolbar, hasBottomNavigation = true)
    }

    private fun setObservers() {
        viewModel.getListOfPhotos()
        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading == true) {
                binding.ivLoadingPhotos.setImageResource(R.drawable.loading_animation)
                binding.ivLoadingPhotos.visibility = View.VISIBLE
            } else {
                binding.ivLoadingPhotos.visibility = View.GONE
            }
        }

        viewModel.networkError.observe(viewLifecycleOwner) { error ->
            if (error == true) {
                binding.ivErrorNetworkPhotos.setImageResource(R.drawable.ic_network_error)
                binding.ivErrorNetworkPhotos.visibility = View.VISIBLE
            } else {
                binding.ivErrorNetworkPhotos.visibility = View.GONE
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.navigateToPhotoDetails.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(PhotosFragmentDirections.actionPhotosFragmentToPhotoDetailsFragment(it))
                viewModel.openPhotoDetailsComplete()
            }
        }
    }

    private fun setAdapter() {
        val horizontalSpacing = resources.getDimensionPixelSize(R.dimen.spacingCategory)
        val spanCount = resources.getInteger(R.integer.spanCountCategory)
        binding.rvListPhotos.adapter = adapter
        binding.rvListPhotos.addItemDecoration(GridItemDecorator(horizontalSpacing, spanCount))
    }
}