package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.compose.ComposeLauncherActivity
import com.example.myapplication.architecture.AppArchitectureActivity
import com.example.myapplication.databinding.ActivityLauncherBinding
import com.example.myapplication.device.DeviceCompatibilityActivity
import com.example.myapplication.entrypoints.EntryPointActivity
import com.example.nativelib.JniLauncherActivity
import com.example.navigation.NavigationActivity
import com.example.predictive_back.PredictiveBackNavigationActivity
import com.example.myapplication.resources.ResourcesActivity
import com.example.myapplication.views.CustomViewActivity

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigation.setOnClickListener {
            startActivity(Intent(this, NavigationActivity::class.java))
        }
        binding.predictiveBackNavigation.setOnClickListener {
            startActivity(Intent(this, PredictiveBackNavigationActivity::class.java))
        }
        binding.resources.setOnClickListener {
            startActivity(Intent(this, ResourcesActivity::class.java))
        }
        binding.device.setOnClickListener {
            startActivity(Intent(this, DeviceCompatibilityActivity::class.java))
        }
        binding.appArchitecture.setOnClickListener {
            startActivity(Intent(this, AppArchitectureActivity::class.java))
        }
        binding.entryPoints.setOnClickListener {
            startActivity(Intent(this, EntryPointActivity::class.java))
        }
        binding.customView.setOnClickListener {
            startActivity(Intent(this, CustomViewActivity::class.java))
        }
        binding.jni.setOnClickListener {
            startActivity(Intent(this, JniLauncherActivity::class.java))
        }
        binding.compose.setOnClickListener {
            startActivity(Intent(this, ComposeLauncherActivity::class.java))
        }
    }
}
