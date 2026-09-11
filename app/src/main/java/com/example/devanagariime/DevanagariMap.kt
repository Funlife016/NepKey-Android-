package com.example.devanagariime

import android.view.KeyEvent

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
        KeyEvent.KEYCODE_X to arrayOf("ष", "क्ष", "", ""),
        KeyEvent.KEYCODE_Y to arrayOf("य", "ज्ञ", "", ""),
        KeyEvent.KEYCODE_Z to arrayOf("आ", "त्र", "", ""),

        KeyEvent.KEYCODE_LEFT_BRACKET  to arrayOf("इ", "ई", "[", "{"),
        KeyEvent.KEYCODE_RIGHT_BRACKET to arrayOf("ओ", "औ", "]", "}"),
        KeyEvent.KEYCODE_SEMICOLON     to arrayOf("उ", "ऊ", ";", ":"),
        KeyEvent.KEYCODE_APOSTROPHE    to arrayOf("ए", "ऐ", "\"", "'"),
        KeyEvent.KEYCODE_COMMA         to arrayOf(",", "।", "<", "॥"),
        KeyEvent.KEYCODE_PERIOD        to arrayOf("्", ">", ".", ""),
        KeyEvent.KEYCODE_SLASH         to arrayOf("?", "\"", "/", ""),
        KeyEvent.KEYCODE_BACKSLASH     to arrayOf("श्र", "\\", "|", ""),

        // Digits: base is now Devanagari (matches the rest of the layout
        // typing Devanagari by default). Shift left empty so !@#$ etc
        // still work exactly as before via system fallthrough. AltGr
        // gives you the regular English digit when needed.
        KeyEvent.KEYCODE_0 to arrayOf("०", "", "0", ""),
        KeyEvent.KEYCODE_1 to arrayOf("१", "", "1", ""),
        KeyEvent.KEYCODE_2 to arrayOf("२", "", "2", ""),
        KeyEvent.KEYCODE_3 to arrayOf("३", "", "3", ""),
        KeyEvent.KEYCODE_4 to arrayOf("४", "", "4", ""),
        KeyEvent.KEYCODE_5 to arrayOf("५", "", "5", ""),
        KeyEvent.KEYCODE_6 to arrayOf("६", "", "6", ""),
        KeyEvent.KEYCODE_7 to arrayOf("७", "", "7", ""),
        KeyEvent.KEYCODE_8 to arrayOf("८", "", "8", ""),
        KeyEvent.KEYCODE_9 to arrayOf("९", "", "9", "")
    )

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
