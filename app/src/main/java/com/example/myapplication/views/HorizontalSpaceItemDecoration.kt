package com.example.myapplication.views

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(
        private val firstItemSpaceWidth: Int,
        private val itemSpaceWidth: Int
    ) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = firstItemSpaceWidth
            } else {
                outRect.left = itemSpaceWidth
            }
            if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)) {
                outRect.right = firstItemSpaceWidth
            }
        }
    }
