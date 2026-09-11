package com.example.devanagariime

import android.view.KeyEvent

/**
 * Each entry: keyCode -> [base, shift, altGr, shiftAltGr]
 * Empty string "" means "no mapping at this level -- fall through to
 * the system default for that key" (used for plain punctuation keys
 * where we don't override every level).
 *
 * This is the ONE file you'll want to edit if you change the layout --
 * everything else in the project just reads from this map.
 */
object DevanagariMap {

    val table: Map<Int, Array<String>> = mapOf(
        KeyEvent.KEYCODE_A to arrayOf("ा", "अ", "", ""),
        KeyEvent.KEYCODE_B to arrayOf("ब", "भ", "", ""),
        KeyEvent.KEYCODE_C to arrayOf("च", "छ", "", ""),
        KeyEvent.KEYCODE_D to arrayOf("द", "ध", "", ""),
        KeyEvent.KEYCODE_E to arrayOf("े", "ै", "", ""),
        KeyEvent.KEYCODE_F to arrayOf("ट", "ठ", "", ""),
        KeyEvent.KEYCODE_G to arrayOf("ग", "घ", "", ""),
        KeyEvent.KEYCODE_H to arrayOf("ह", "ः", "", ""),
        KeyEvent.KEYCODE_I to arrayOf("ि", "ी", "", ""),
        KeyEvent.KEYCODE_J to arrayOf("ज", "झ", "", ""),
        KeyEvent.KEYCODE_K to arrayOf("क", "ख", "", ""),
        KeyEvent.KEYCODE_L to arrayOf("ल", "ञ", "", ""),
        KeyEvent.KEYCODE_M to arrayOf("म", "ं", "", ""),
        KeyEvent.KEYCODE_N to arrayOf("न", "ण", "", ""),
        KeyEvent.KEYCODE_O to arrayOf("ो", "ौ", "", ""),
        KeyEvent.KEYCODE_P to arrayOf("प", "फ", "", ""),
        KeyEvent.KEYCODE_Q to arrayOf("ँ", "ङ", "", ""),
        KeyEvent.KEYCODE_R to arrayOf("र", "ृ", "", ""),
        KeyEvent.KEYCODE_S to arrayOf("स", "श", "", ""),
        KeyEvent.KEYCODE_T to arrayOf("त", "थ", "", ""),
        KeyEvent.KEYCODE_U to arrayOf("ु", "ू", "", ""),
        KeyEvent.KEYCODE_V to arrayOf("ड", "ढ", "", ""),
        KeyEvent.KEYCODE_W to arrayOf("व", "ऋ", "", ""),
        KeyEvent.KEYCODE_X to arrayOf("ष", "क्ष", "", ""),      // shift = full conjunct, no workaround needed
        KeyEvent.KEYCODE_Y to arrayOf("य", "ज्ञ", "", ""),      // shift = full conjunct
        KeyEvent.KEYCODE_Z to arrayOf("आ", "त्र", "", ""),      // shift = full conjunct

        KeyEvent.KEYCODE_LEFT_BRACKET  to arrayOf("इ", "ई", "[", "{"),
        KeyEvent.KEYCODE_RIGHT_BRACKET to arrayOf("ओ", "औ", "]", "}"),
        KeyEvent.KEYCODE_SEMICOLON     to arrayOf("उ", "ऊ", ";", ":"),
        KeyEvent.KEYCODE_APOSTROPHE    to arrayOf("ए", "ऐ", "\"", "'"),
        KeyEvent.KEYCODE_COMMA         to arrayOf(",", "।", "<", "॥"),
        KeyEvent.KEYCODE_PERIOD        to arrayOf("्", ">", ".", ""),
        KeyEvent.KEYCODE_SLASH         to arrayOf("?", "\"", "/", ""),
        KeyEvent.KEYCODE_BACKSLASH     to arrayOf("श्र", "\\", "|", "")  // base = full conjunct
    )

    /**
     * Returns the string to commit for this keyCode + modifier state,
     * or null if this key isn't in our table (caller should let the
     * event fall through to default system handling -- e.g. Enter,
     * Backspace, arrow keys, digits, Tab, etc. are all untouched).
     */
    fun lookup(keyCode: Int, shiftPressed: Boolean, altGrPressed: Boolean): String? {
        val entry = table[keyCode] ?: return null
        val index = when {
            shiftPressed && altGrPressed -> 3
            altGrPressed -> 2
            shiftPressed -> 1
            else -> 0
        }
        val result = entry[index]
        return if (result.isEmpty()) null else result
    }
}
