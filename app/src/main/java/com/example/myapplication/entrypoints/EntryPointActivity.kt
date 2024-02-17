package com.example.myapplication.entrypoints

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.documents.DocumentsLauncherActivity
import com.example.myapplication.databinding.ActivityEntryPointBinding
import com.example.shortcuts.ShortcutLauncherActivity

class EntryPointActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEntryPointBinding.inflate(layoutInflater)
        title = this::class.java.simpleName
        setContentView(binding.root)
        binding.documents.setOnClickListener {
            startActivity(Intent(this, DocumentsLauncherActivity::class.java))
        }
        binding.shortcut.setOnClickListener {
            startActivity(Intent(this, ShortcutLauncherActivity::class.java))
        }
    }
}
