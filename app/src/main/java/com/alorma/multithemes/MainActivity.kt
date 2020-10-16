package com.alorma.multithemes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val themeSwitcher: ThemeSwitcher by lazy {
        DebugThemeSwitcher(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        themeSwitcher.applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.theme_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_title) {
            themeSwitcher.pickNewTheme(this)
        }
        return super.onOptionsItemSelected(item)
    }

}