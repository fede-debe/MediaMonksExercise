package com.example.mediamonksexercise.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecorator(
    private val spacing: Int,
    private val mGridSize: Int
) : RecyclerView.ItemDecoration() {
    private var mNeedLeftSpacing = false
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val frameWidth =
            ((parent.width - spacing.toFloat() * (mGridSize - 1)) / mGridSize).toInt()
        val padding = parent.width / mGridSize - frameWidth
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
        if (itemPosition < mGridSize) {
            outRect.top = 0
        } else {
            outRect.top = spacing
        }
        if (itemPosition % mGridSize == 0) {
            outRect.left = 0
            outRect.right = padding
            mNeedLeftSpacing = true
        } else if ((itemPosition + 1) % mGridSize == 0) {
            mNeedLeftSpacing = false
            outRect.right = 0
            outRect.left = padding
        } else if (mNeedLeftSpacing) {
            mNeedLeftSpacing = false
            outRect.left = spacing - padding
            if ((itemPosition + 2) % mGridSize == 0) {
                outRect.right = spacing - padding
            } else {
                outRect.right = spacing / 2
            }
        } else if ((itemPosition + 2) % mGridSize == 0) {
            mNeedLeftSpacing = false
            outRect.left = spacing / 2
            outRect.right = spacing - padding
        } else {
            mNeedLeftSpacing = false
            outRect.left = spacing / 2
            outRect.right = spacing / 2
        }
        outRect.bottom = 0
    }
}