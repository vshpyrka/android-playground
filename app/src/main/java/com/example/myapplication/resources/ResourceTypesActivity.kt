package com.example.myapplication.resources

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityResourceTypesBinding
import com.example.resources.types.ResourceTypeAnimActivity
import com.example.resources.types.ResourceTypeColorStateListActivity
import com.example.resources.types.ResourceTypeDrawableActivity
import com.example.resources.types.ResourceTypeFontActivity
import com.example.resources.types.ResourceTypeMenuActivity
import com.example.resources.types.ResourceTypeMoreActivity
import com.example.resources.types.ResourceTypeStringActivity

class ResourceTypesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResourceTypesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.anim.setOnClickListener {
            startActivity(Intent(this, ResourceTypeAnimActivity::class.java))
        }
        binding.colorStateList.setOnClickListener {
            startActivity(Intent(this, ResourceTypeColorStateListActivity::class.java))
        }
        binding.drawables.setOnClickListener {
            startActivity(Intent(this, ResourceTypeDrawableActivity::class.java))
        }
        binding.menu.setOnClickListener {
            startActivity(Intent(this, ResourceTypeMenuActivity::class.java))
        }
        binding.strings.setOnClickListener {
            startActivity(Intent(this, ResourceTypeStringActivity::class.java))
        }
        binding.fonts.setOnClickListener {
            startActivity(Intent(this, ResourceTypeFontActivity::class.java))
        }
        binding.more.setOnClickListener {
            startActivity(Intent(this, ResourceTypeMoreActivity::class.java))
        }
    }
}
