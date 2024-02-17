package com.example.myapplication.architecture

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.datastore.DataStoreLauncherActivity
import com.example.viewmodel.ViewModelActivities
import com.example.workmanager.WorkManagerActivity
import com.example.myapplication.databinding.ActivityAppArchitectureBinding

class AppArchitectureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppArchitectureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycle.setOnClickListener {
            startActivity(Intent(this, LifecycleActivity::class.java))
        }
        binding.viewModel.setOnClickListener {
            startActivity(Intent(this, ViewModelActivities::class.java))
        }
        binding.workManager.setOnClickListener {
            startActivity(Intent(this, WorkManagerActivity::class.java))
        }
        binding.datastore.setOnClickListener {
            startActivity(Intent(this, DataStoreLauncherActivity::class.java))
        }
    }
}
