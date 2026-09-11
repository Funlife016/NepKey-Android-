package com.example.devanagariime

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast

class DevanagariIME : InputMethodService() {

    // When false, every key passes straight through as normal English --
    // toggled with Ctrl+Space.
    private var devanagariEnabled = true

    override fun onCreateInputView(): View? = null

    override fun onEvaluateInputViewShown(): Boolean = false

    override fun onEvaluateFullscreenMode(): Boolean = false

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val isPhysical = event.device?.isVirtual == false
        if (!isPhysical) {
            return super.onKeyDown(keyCode, event)
        }

        val ctrlPressed = event.isCtrlPressed

        // Ctrl+Space toggles Devanagari on/off, regardless of current state.
        if (ctrlPressed && keyCode == KeyEvent.KEYCODE_SPACE) {
            devanagariEnabled = !devanagariEnabled
            Toast.makeText(
                applicationContext,
                if (devanagariEnabled) "Devanagari ON" else "Devanagari OFF (English)",
                Toast.LENGTH_SHORT
            ).show()
            return true
        }

        // Any other Ctrl combo (Ctrl+T, Ctrl+C, Ctrl+V, etc.) -- never
        // intercept, always let the system/app handle it normally.
        if (ctrlPressed) {
            return super.onKeyDown(keyCode, event)
        }

        // Passthrough mode: act like a completely normal keyboard.
        if (!devanagariEnabled) {
            return super.onKeyDown(keyCode, event)
        }

        val shiftPressed = event.isShiftPressed
        val altGrPressed = (event.metaState and KeyEvent.META_ALT_RIGHT_ON) != 0

        val output = DevanagariMap.lookup(keyCode, shiftPressed, altGrPressed)

        if (output != null) {
            currentInputConnection?.commitText(output, 1)
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
    }
}
