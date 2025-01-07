package com.avcoding.multioverlapimageview

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.pow

class SimpleTreeLayoutManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        detachAndScrapAttachedViews(recycler)

        if (itemCount == 0) return

        val startX = width / 2 // Center horizontally
        val startY = 50        // Starting Y position
        val verticalOffset = 150 // Vertical distance between levels
        val horizontalOffset = 100 // Horizontal distance between images on the same level

        var currentX = startX
        var currentY = startY
        var level = 0 // Tracks the level in the tree
        var itemsInLevel = 1 // Tracks the number of items in the current level
        var countInCurrentLevel = 0 // Counter for current level items

        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view, 0, 0)

            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)

            // Calculate X position based on level and items in the level
            val xOffset = (countInCurrentLevel - (itemsInLevel / 2)) * horizontalOffset
            currentX = startX + xOffset

            // Layout the view
            layoutDecorated(
                view,
                currentX - width / 2,
                currentY,
                currentX + width / 2,
                currentY + height
            )

            countInCurrentLevel++
            if (countInCurrentLevel == itemsInLevel) {
                // Move to the next level
                level++
                itemsInLevel = 2.0.pow(level).toInt() // Each level doubles the number of items
                countInCurrentLevel = 0
                currentY += verticalOffset
            }
        }
    }

    override fun canScrollVertically(): Boolean = false
}
