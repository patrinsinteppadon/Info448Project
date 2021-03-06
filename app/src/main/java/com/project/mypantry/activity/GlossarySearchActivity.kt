package com.project.mypantry.activity

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import com.project.mypantry.R
import com.project.mypantry.adapters.GlossaryListAdapter
import com.project.mypantry.application.PantryApp
import kotlinx.android.synthetic.main.activity_glossary_search.*

class GlossarySearchActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    lateinit var app: PantryApp
    lateinit var adapter: GlossaryListAdapter


    companion object {
        const val FOR_PANTRY = "pantry"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glossary_search)
        title = "Pick an Ingredient"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // initialize app and adapter
        app = (applicationContext as PantryApp)
        adapter = GlossaryListAdapter(this, app.glossaryManager.glossary)

        // mount adapter to listView
        listView = lvIngredientType
        listView.adapter = adapter

        // set item click listeners for listView
        listView.setOnItemClickListener { _, _, _, id ->
            // if for pantry
            if (intent.getBooleanExtra(FOR_PANTRY, false)) {
                val intent: Intent = Intent(this, IngredientDetailActivity::class.java).apply {
                    putExtra(
                        IngredientDetailActivity.ING_TYPE_EXTRA,
                        app.glossaryManager.getIngredientType(id.toInt())
                    )
                }
                startActivityForResult(intent, 123)
            } else { // if for shopping list
                app.glossaryManager.getIngredientType((id.toInt()))?.let {
                    app.shoppingListManager.add(it)
                }
                setResult(RESULT_OK)
                finish()
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.glossary_search_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search_bar)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}