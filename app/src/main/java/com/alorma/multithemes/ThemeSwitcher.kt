package com.alorma.multithemes

import android.app.Activity

interface ThemeSwitcher {
  fun applyTheme(activity: Activity)

  fun pickNewTheme(activity: Activity)
}