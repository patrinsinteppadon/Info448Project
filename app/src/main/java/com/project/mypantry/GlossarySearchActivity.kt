package com.project.mypantry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.project.mypantry.application.PantryApp
import com.project.mypantry.objects.IngredientType
import kotlinx.android.synthetic.main.activity_glossary_search.*

class GlossarySearchActivity : AppCompatActivity() {
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glossary_search)

        val app: PantryApp = (applicationContext as PantryApp)
        val glossaryManager = app.glossaryManager



    }
}