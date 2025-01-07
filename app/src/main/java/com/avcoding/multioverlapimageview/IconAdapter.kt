package com.avcoding.multioverlapimageview

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class IconAdapter(private val icons: List<Int>) : RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

    inner class IconViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(150, 150) // Set size for icons
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return IconViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        holder.imageView.setImageResource(icons[position])
        // Optional: Add logic for highlighting the center icon
        if (position == icons.size / 2) {
            holder.imageView.scaleX = 1.2f
            holder.imageView.scaleY = 1.2f
        } else {
            holder.imageView.scaleX = 1.0f
            holder.imageView.scaleY = 1.0f
        }
    }

    override fun getItemCount(): Int = icons.size
}
