package com.example.devanagariime

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo

/**
 * A "headless" IME: no on-screen keyboard UI at all (onCreateInputView
 * returns null / onEvaluateInputViewShown returns false), it only exists
 * to intercept HARDWARE key events from a physical keyboard and commit
 * the correct Devanagari string.
 *
 * To use it: connect the physical keyboard, then switch the active
 * input method to "Devanagari QWERTY" (long-press the space bar on
 * whatever IME is currently active, or Settings > System > Languages
 * & input > On-screen keyboard > Manage keyboards, enable this one,
 * then switch to it). Once selected, physical typing goes through
 * this service even though no on-screen keyboard appears.
 */
class DevanagariIME : InputMethodService() {

    override fun onCreateInputView(): View? = null

    override fun onEvaluateInputViewShown(): Boolean = false

    override fun onEvaluateFullscreenMode(): Boolean = false

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // Only intercept if this came from a physical/external keyboard.
        // (event.device.isVirtual is false for real hardware; this check
        // keeps on-screen system keyboards, if any are layered, unaffected.)
        val isPhysical = event.device?.isVirtual == false
        if (!isPhysical) {
            return super.onKeyDown(keyCode, event)
        }

        val shiftPressed = event.isShiftPressed
        // Right Alt is the conventional AltGr key on most physical
        // keyboards/layouts. META_ALT_RIGHT_ON is set specifically for
        // right-alt, distinguishing it from left-alt.
        val altGrPressed = (event.metaState and KeyEvent.META_ALT_RIGHT_ON) != 0

        val output = DevanagariMap.lookup(keyCode, shiftPressed, altGrPressed)

        if (output != null) {
            currentInputConnection?.commitText(output, 1)
            return true // consumed -- don't let the system also process it
        }

        // Not in our table (Enter, Backspace, arrows, digits, Tab, etc.)
        // -- let the system handle it normally.
        return super.onKeyDown(keyCode, event)
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
    }
}
