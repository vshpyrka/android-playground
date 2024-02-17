package com.example.myapplication.device

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.BidiFormatter
import androidx.core.text.TextDirectionHeuristicsCompat
import com.example.myapplication.databinding.ActivitySupportDifferentLanguageLocalesBinding
import java.util.*

class SupportDifferentLanguageLocalesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySupportDifferentLanguageLocalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val string = "האם התכוונת ל %s?"
        val mySuggestion = "15 Bay Street, Laurel, CA"
        val myBidiFormatter = BidiFormatter.getInstance(Locale.getDefault())
        if (myBidiFormatter.isRtlContext) {
            binding.text.text = String.format(string, myBidiFormatter.unicodeWrap(mySuggestion, TextDirectionHeuristicsCompat.ANYRTL_LTR))
        } else {
            binding.text.text = "Change locale to RTL to see the result"
        }
        resources.configuration.layoutDirection
    }
}
