package com.alorma.multithemes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.card.MaterialCardView
import com.google.android.material.color.MaterialColors

class MainActivity : AppCompatActivity() {

    private val themeSwitcher: ThemeSwitcher by lazy {
        DebugThemeSwitcher(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        themeSwitcher.applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        val card1 = findViewById<MaterialCardView>(R.id.card1)
        val card2 = findViewById<MaterialCardView>(R.id.card2)
        val card3 = findViewById<MaterialCardView>(R.id.card3)

        updateCardColor(card1, R.color.color_primary_8)
        updateCardColor(card2, R.color.color_primary_50)
        updateCardColor(card3, R.color.color_primary)
    }

    private fun updateCardColor(card: MaterialCardView, @ColorRes colorStateListRes: Int) {
        val color = AppCompatResources.getColorStateList(this, colorStateListRes).defaultColor

        val backgroundColor = MaterialColors.getColor(card, R.attr.colorSurface)

        val newColor = MaterialColors.layer(
            backgroundColor,
            color,
        )

        card.setCardBackgroundColor(newColor)
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