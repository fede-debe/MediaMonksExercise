package com.example.mediamonksexercise.presentation.ui.album

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mediamonksexercise.MyApplication
import com.example.mediamonksexercise.R
import com.example.mediamonksexercise.databinding.FragmentAlbumBinding
import com.example.mediamonksexercise.presentation.adapter.AlbumAdapter
import com.example.mediamonksexercise.utils.setDisplayEdgeToEdge
import javax.inject.Inject

class AlbumFragment : Fragment() {

    private lateinit var binding: FragmentAlbumBinding
    private val adapter = AlbumAdapter()

    @Inject
    lateinit var albumViewModel: AlbumViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        (requireContext().applicationContext as MyApplication).appComponent.injectAlbum(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setObservers()
        setDisplayEdgeToEdge(binding.root, binding.toolbar, hasBottomNavigation = true)
    }

    private fun setObservers() {
        albumViewModel.getListOfAlbums()
        albumViewModel.albums.observe(viewLifecycleOwner) { albums ->
            adapter.submitList(albums)
        }

        albumViewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading == true) {
                binding.ivLoadingAlbums.setImageResource(R.drawable.loading_animation)
                binding.ivLoadingAlbums.visibility = View.VISIBLE
            } else {
                binding.ivLoadingAlbums.visibility = View.GONE
            }
        }

        albumViewModel.networkError.observe(viewLifecycleOwner) { error ->
            if (error == true) {
                binding.ivErrorNetworkAlbums.setImageResource(R.drawable.ic_network_error)
                binding.ivErrorNetworkAlbums.visibility = View.VISIBLE
            } else {
                binding.ivErrorNetworkAlbums.visibility = View.GONE
            }
        }

        albumViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAdapter() {
        binding.rvListAlbums.adapter = adapter
    }
}