package com.example.mediamonksexercise.utils

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun setDisplayEdgeToEdge(
    bindingRoot: View,
    toolbar: Toolbar? = null,
    hasBottomNavigation: Boolean = false
) {
    ViewCompat.setOnApplyWindowInsetsListener(bindingRoot) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures())
        val insetBottom = if (hasBottomNavigation) 0 else insets.bottom
        view.updatePadding(insets.left, 0, insets.right, insetBottom)
        toolbar?.setPadding(
            toolbar.paddingLeft,
            insets.top,
            toolbar.paddingRight,
            toolbar.paddingBottom
        )
        WindowInsetsCompat.CONSUMED
    }
}