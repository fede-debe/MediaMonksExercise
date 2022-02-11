package com.example.mediamonksexercise.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mediamonksexercise.R
import com.example.mediamonksexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val flowsWithBottomBarDestinationIds = setOf(
        R.id.albumFragment,
        R.id.photosFragment
    )

    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (navController.graph.id == R.id.nav_graph) navController.navigateUp()
        else super.onSupportNavigateUp()
    }

    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        if (flowsWithBottomBarDestinationIds.contains(destination.id)) {
            binding.bottomNavigation.visibility = View.VISIBLE
        } else {
            binding.bottomNavigation.visibility = View.GONE
        }
    }

    private fun setNavigation() {
        navController.addOnDestinationChangedListener(listener)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}