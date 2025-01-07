package com.avcoding.multioverlapimageview

import androidx.recyclerview.widget.RecyclerView

class OverlapLayoutManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        detachAndScrapAttachedViews(recycler)

        if (itemCount == 0) return

        val centerX = width / 2 // Center of the RecyclerView
        val overlap = 50 // Amount of overlap (adjust as needed)

        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view, 0, 0)

            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)

            // Calculate position with overlap
            val offsetX = centerX - width / 2 + (i - itemCount / 2) * overlap
            layoutDecorated(
                view,
                offsetX,
                (height / 4),
                offsetX + width,
                height + height / 4
            )
        }
    }

    override fun canScrollHorizontally(): Boolean = false // No horizontal scrolling needed
}
