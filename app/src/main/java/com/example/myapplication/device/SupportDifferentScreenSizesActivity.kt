package com.example.myapplication.device

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.window.layout.WindowMetricsCalculator
import com.vshpyrka.playground.databinding.ActivitySupportDifferentScreenSizesBinding
import com.example.myapplication.utils.applyWindowInsets

enum class WindowSizeClass { COMPACT, MEDIUM, EXPANDED }

class SupportDifferentScreenSizesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySupportDifferentScreenSizesBinding.inflate(layoutInflater)
        binding.root.applyWindowInsets()
        setContentView(binding.root)

        binding.container.addView(
            object : View(this) {

                override fun onConfigurationChanged(newConfig: Configuration?) {
                    super.onConfigurationChanged(newConfig)
                    computeWindowSizeClasses()
                }
            }
        )
        computeWindowSizeClasses()
    }

    private fun computeWindowSizeClasses() {
        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        val widthDp = metrics.bounds.width() / resources.displayMetrics.density
        val widthWindowSizeClass = when {
            widthDp < 600 -> WindowSizeClass.COMPACT
            widthDp < 840 -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }
        val heightDp = metrics.bounds.height() / resources.displayMetrics.density
        val heightWindowSizeClass = when {
            heightDp < 480 -> WindowSizeClass.COMPACT
            heightDp < 900 -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }
        println("AAA $widthDp x $heightDp $widthWindowSizeClass x $heightWindowSizeClass")
    }
}
