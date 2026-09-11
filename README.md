## How it works
Unlike a static `.kcm` physical-keyboard layout (which, like Linux XKB,
can only emit ONE Unicode codepoint per key+modifier), this is a real
IME service. It intercepts hardware `KeyEvent`s directly and calls
`commitText()` with whatever string we want -- including the full
3-codepoint conjuncts (क्ष, ज्ञ, त्र, श्र) in a single keystroke, with
NO XCompose-style workaround needed. That's the one thing Android does
more easily than Linux here.

## To use, on the tablet
1. Open the app once (it just shows instructions + a settings shortcut).
2. Tap "Open Input Method Settings" > enable "Devanagari QWERTY".
3. Attach the Book Cover Keyboard.
4. In any text field, switch active keyboard to "Devanagari QWERTY"
   (long-press spacebar on the current keyboard, or use the little
   keyboard icon in the nav bar / notification).
5. Type -- no on-screen keyboard will appear (by design), but your
   physical keys now produce Devanagari per our table.

## What's editable
Everything you'd want to tweak lives in ONE file:
`app/src/main/java/com/example/devanagariime/DevanagariMap.kt`
Change a mapping, rebuild, reinstall -- that's it.

## Known limitations / things to test carefully
- **AltGr detection**: uses `KeyEvent.META_ALT_RIGHT_ON`, which assumes
  the Book Cover Keyboard has a distinguishable Right Alt key that
  Android reports correctly. Some external keyboards report Left/Right
  Alt identically. If AltGr-level characters (the bracket-row extras:
  `[ ] ; ' , .` producing literal punctuation) don't trigger, this is
  the first thing to debug -- test by logging `event.metaState` for a
  few keypresses.
- **Digit row and number keys**: currently untouched, they'll type
  normal 0-9 as usual (matches our plan -- Devanagari numerals were
  deferred, not forgotten).
- **Other IMEs / apps that also want physical key events**: this
  service claims ALL physical key events while active, since it's the
  selected system IME. Switching back to your normal keyboard for
  English typing means switching the active IME, same as switching
  between any two keyboards on Android.
- **No visual feedback**: since there's no on-screen keyboard, there's
  no visual key-highlighting or Shift indicator like a normal IME
  provides. Worth living with it for a bit before deciding if a
  minimal status indicator is worth adding later.

## Next steps once basic typing works
- Devanagari digit row (०-९) via AltGr on the number keys
- A tiny always-on-top overlay showing which layer (base/shift/altgr)
  is currently active, since there's no visual keyboard to look at
- Package as a proper installable APK / consider F-Droid submission
  once stable, so the wider Nepali-Linux-Android crowd can use it too
