package com.example.myapplication.device

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.embeddedactivity.ChatListActivity
import com.example.mediaprojection.MediaProjectionActivity
import com.vshpyrka.playground.databinding.ActivityDeviceCompatibilityBinding
import com.example.myapplication.utils.applyWindowInsets
import com.example.slidingpanelayout.SlidingPaneLayoutAdaptiveDesignActivity

class DeviceCompatibilityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDeviceCompatibilityBinding.inflate(layoutInflater)
        binding.root.applyWindowInsets()
        setContentView(binding.root)

        binding.aspectRatio.setOnClickListener {
            startActivity(Intent(this, ResizeAspectRatioActivity::class.java))
        }

        binding.locales.setOnClickListener {
            startActivity(Intent(this, SupportDifferentLanguageLocalesActivity::class.java))
        }

        binding.mediaProjection.setOnClickListener {
            startActivity(Intent(this, MediaProjectionActivity::class.java))
        }

        binding.supportedScreenSize.setOnClickListener {
            startActivity(Intent(this, SupportDifferentScreenSizesActivity::class.java))
        }

        binding.slidingPaneAdaptiveDesign.setOnClickListener {
            startActivity(Intent(this, SlidingPaneLayoutAdaptiveDesignActivity::class.java))
        }

        binding.embeddedActivity.setOnClickListener {
            startActivity(Intent(this, ChatListActivity::class.java))
        }
    }
}
