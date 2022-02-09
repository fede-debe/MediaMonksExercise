package com.example.mediamonksexercise.presentation.ui.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mediamonksexercise.R
import com.example.mediamonksexercise.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}