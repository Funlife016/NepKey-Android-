package com.example.devanagariime

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 96, 48, 48)
        }

        val instructions = TextView(this).apply {
            text = "Setup:\n\n" +
                "1. Tap the button below to open Input Method settings.\n" +
                "2. Enable \"Devanagari QWERTY\".\n" +
                "3. Connect your physical keyboard.\n" +
                "4. Switch to this keyboard (long-press the spacebar in " +
                "any text field, or use the input method switcher).\n" +
                "5. Ctrl+Space toggles between Devanagari and normal " +
                "English typing while this keyboard is active."
            textSize = 16f
        }

        val openSettingsButton = Button(this).apply {
            text = "Open Input Method Settings"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
            }
        }

        layout.addView(instructions)
        layout.addView(openSettingsButton)
        setContentView(layout)
    }
}
