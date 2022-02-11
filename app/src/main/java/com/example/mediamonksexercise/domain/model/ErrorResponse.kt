package com.example.mediamonksexercise.domain.model

import android.content.Context
import android.widget.Toast

data class ErrorResponse (
    val code: Int,
    val message: String?
) {
    fun showToast(context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
