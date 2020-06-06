package com.project.mypantry

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.project.mypantry.adapters.GlossaryListAdapter
import com.project.mypantry.application.PantryApp
import com.project.mypantry.objects.IngredientType
import kotlinx.android.synthetic.main.activity_glossary_search.*

class GlossarySearchActivity : AppCompatActivity() {
    lateinit var listView: ListView

    companion object {
        const val FOR_PANTRY = "ripblackmambathelegend24"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glossary_search)
        title = "Pick an Ingredient"

        val app: PantryApp = (applicationContext as PantryApp)
        val glossaryManager = app.glossaryManager

        listView = lvIngredientType
        val adapter = GlossaryListAdapter(this, glossaryManager.glossary)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, _, id ->
            if (intent.getBooleanExtra(FOR_PANTRY, false)) {
                val intent: Intent = Intent(this, IngredientDetailActivity::class.java).apply {
                    putExtra(
                        IngredientDetailActivity.ING_TYPE_EXTRA,
                        glossaryManager.getIngredientType(id.toInt())
                    )
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Adding to shopping list", Toast.LENGTH_SHORT).show()
            }
        }

        // set on text change listener

    }
}