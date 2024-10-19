package com.example.myapplication.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCustomViewBinding
import com.example.myapplication.utils.applyWindowInsets
import kotlin.random.Random

class CustomViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        binding.root.applyWindowInsets()
        setContentView(binding.root)

        with(binding) {
            contact.iconView.setImageResource(com.example.views.R.drawable.ic_cat)
            contact.titleView.text = "John Doe"
            contact.subtitleView.text =
                "Member of the Sales team. Will help you find the right products and pricing for your business"
            contact.setOnClickListener {
                Toast.makeText(this@CustomViewActivity, "Message", Toast.LENGTH_SHORT).show()
            }
        }
        runProgress()
    }

    private fun runProgress() {
        binding.progress.postDelayed(
            {
                binding.progress.setProgress(Random.nextDouble(0.0, 100.0).toFloat())
                runProgress()
            },
            2000
        )
    }
}
