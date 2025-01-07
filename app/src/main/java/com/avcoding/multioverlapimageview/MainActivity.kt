package com.avcoding.multioverlapimageview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val images = listOf(
            R.drawable.ic_calm,
            R.drawable.ic_confused,
            R.drawable.ic_concentrated,
            R.drawable.ic_calm,
            R.drawable.ic_confused,
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = IconAdapter(images)
        recyclerView.layoutManager = OverlapIconLayoutManager()

    }
}