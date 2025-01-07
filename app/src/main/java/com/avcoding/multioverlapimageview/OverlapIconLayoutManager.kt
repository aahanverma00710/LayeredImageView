package com.avcoding.multioverlapimageview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OverlapIconLayoutManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        detachAndScrapAttachedViews(recycler)

        if (itemCount == 0) return

        val overlapOffset = 60 // Horizontal overlap between icons
        val centerX = width / 2 // Horizontal center of RecyclerView
        val centerY = height / 2 // Vertical center of RecyclerView

        // Place the middle item (or closest to middle) at the center
        val middleIndex = itemCount / 2

        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view, 0, 0)

            val iconWidth = getDecoratedMeasuredWidth(view)
            val iconHeight = getDecoratedMeasuredHeight(view)

            // Calculate X position
            val offsetFromCenter = Math.abs(middleIndex - i) * overlapOffset
            val xPosition = if (i <= middleIndex) {
                centerX - offsetFromCenter // Items before or at center go left
            } else {
                centerX + offsetFromCenter // Items after center go right
            }

            // Center vertically
            layoutDecorated(
                view,
                xPosition - iconWidth / 2,
                centerY - iconHeight / 2,
                xPosition + iconWidth / 2,
                centerY + iconHeight / 2
            )

            // Adjust Z-order: Place views behind the center as index moves away
            view.translationZ = (middleIndex - Math.abs(middleIndex - i)).toFloat()
        }
    }

    override fun canScrollHorizontally(): Boolean = false
    override fun canScrollVertically(): Boolean = false
}
