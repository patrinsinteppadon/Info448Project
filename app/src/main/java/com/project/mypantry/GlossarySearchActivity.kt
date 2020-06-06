package com.project.mypantry

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.project.mypantry.adapters.GlossaryListAdapter
import com.project.mypantry.application.PantryApp
import com.project.mypantry.objects.IngredientType
import kotlinx.android.synthetic.main.activity_glossary_search.*

class GlossarySearchActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var app: PantryApp
    lateinit var adapter: GlossaryListAdapter


    companion object {
        const val FOR_PANTRY = "ripblackmambathelegend24"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glossary_search)
        title = "Pick an Ingredient"

        // initialize app and adapter
        app = (applicationContext as PantryApp)
        val glossaryManager = app.glossaryManager
        adapter = GlossaryListAdapter(this, glossaryManager.glossary)

        // mount adapter to listView
        listView = lvIngredientType
        listView.adapter = adapter

        // set item click listeners for listView
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

        // set up search bar


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.glossary_search_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search_bar)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (query != null) {
//                    adapter.updateGlossaryList(app.glossaryManager.search(query))
//                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    adapter.updateGlossaryList(app.glossaryManager.search(newText))
                }
                return true
            }

        })

        searchView.setOnCloseListener {
            searchView.setQuery("", true)
            searchView.clearFocus()
            true
        }

        return true

    }
}