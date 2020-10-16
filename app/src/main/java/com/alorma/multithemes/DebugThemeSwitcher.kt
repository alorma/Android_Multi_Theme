package com.alorma.multithemes

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DebugThemeSwitcher(context: Context) : ThemeSwitcher {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("debug_theme_switcher", Context.MODE_PRIVATE)

    private var currentTheme: Int?
        get() = sharedPreferences.getInt("currentTheme", 0).takeIf { it != 0 }
        set(value) = sharedPreferences.edit {
            putInt("currentTheme", value ?: 0)
        }

    override fun applyTheme(activity: Activity) {
        currentTheme?.let {
            activity.setTheme(it)
        }
    }

    override fun pickNewTheme(activity: Activity) {
        val themes = mutableMapOf(
            "<default>" to null,
            "Purple" to R.style.Theme_Purple,
            "Orange" to R.style.Theme_Orange,
            "Green" to R.style.Theme_Green,
        )

        MaterialAlertDialogBuilder(activity)
            .setTitle("Switch theme")
            .setItems(themes.keys.toTypedArray()) { dialog, position ->
                currentTheme = themes.values.toList()[position]
                activity.recreate()
                dialog.dismiss()
            }
            .show()
    }
}