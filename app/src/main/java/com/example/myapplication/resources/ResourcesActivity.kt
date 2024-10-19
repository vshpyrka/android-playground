package com.example.myapplication.resources

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.language.PerAppLanguageActivity
import com.example.myapplication.databinding.ActivityResourcesBinding
import com.example.myapplication.utils.applyWindowInsets
import com.example.resources.ComplexXMLResourceActivity
import com.example.resources.ConfigurationChangeActivity
import com.example.resources.DrawableLocalizedResourceActivity
import com.example.resources.InternationalizationResourceActivity
import com.example.resources.RTLResourceActivity

class ResourcesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResourcesBinding.inflate(layoutInflater)
        binding.root.applyWindowInsets()
        setContentView(binding.root)

        binding.localizedDrawableResource.setOnClickListener {
            startActivity(Intent(this, DrawableLocalizedResourceActivity::class.java))
        }
        binding.rtlResource.setOnClickListener {
            startActivity(Intent(this, RTLResourceActivity::class.java))
        }
        binding.configChange.setOnClickListener {
            startActivity(Intent(this, ConfigurationChangeActivity::class.java))
        }
        binding.internationalization.setOnClickListener {
            startActivity(Intent(this, InternationalizationResourceActivity::class.java))
        }
        binding.complexXml.setOnClickListener {
            startActivity(Intent(this, ComplexXMLResourceActivity::class.java))
        }
        binding.resourceTypes.setOnClickListener {
            startActivity(Intent(this, ResourceTypesActivity::class.java))
        }
        binding.perAppLanguage.setOnClickListener {
            startActivity(Intent(this, PerAppLanguageActivity::class.java))
        }
    }
}
