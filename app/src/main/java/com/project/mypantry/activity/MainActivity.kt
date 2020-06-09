package com.project.mypantry.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.mypantry.R
import com.project.mypantry.application.PantryApp
import com.project.mypantry.fragments.PantryListFragment
import com.project.mypantry.fragments.RecipeListFragment
import com.project.mypantry.fragments.ShoppingListFragment
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    OnRecipeClickListener,
    OnPantryClickListener,
    OnShoppingClickListener {
    lateinit var pantryApp: PantryApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pantryApp = application as PantryApp
        onPantryIconClick()

        pantryButton.setOnClickListener {
            onPantryIconClick()
        }
        recipeButton.setOnClickListener {
            onRecipeIconClick()
        }
        groceryButton.setOnClickListener {
            onGroceryIconClick()
        }
        addButton.setOnClickListener {
            onAddClick()
        }
    }

    private fun getPantryListFragment() = supportFragmentManager.findFragmentByTag(
        PantryListFragment.TAG)

    private fun getRecipeListFragment() = supportFragmentManager.findFragmentByTag(
        RecipeListFragment.TAG)

    private fun getShoppingListFragment() = supportFragmentManager.findFragmentByTag(
        ShoppingListFragment.TAG)

    private fun onPantryIconClick() {
        var pantryListFragment = getPantryListFragment()
        if (pantryListFragment == null) {
            pantryListFragment = PantryListFragment()
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer, pantryListFragment, PantryListFragment.TAG)
                .addToBackStack(PantryListFragment.TAG)
                .commit()
        }
    }

    private fun onRecipeIconClick() {
        var recipeListFragment = getRecipeListFragment()
        if (recipeListFragment == null) {
            recipeListFragment =
                RecipeListFragment()
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer, recipeListFragment, RecipeListFragment.TAG)
                .addToBackStack(RecipeListFragment.TAG)
                .commit()
        }
    }

    private fun onGroceryIconClick() {
        var groceryListFragment = getShoppingListFragment()
        if (groceryListFragment == null) {
            groceryListFragment = ShoppingListFragment()
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer, groceryListFragment, ShoppingListFragment.TAG)
                .addToBackStack(ShoppingListFragment.TAG)
                .commit()
        }
    }
//
    private fun onAddClick() {
        if (supportFragmentManager.primaryNavigationFragment is RecipeListFragment) {
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("isAdding", true)
            startActivity(intent)
        } else if (supportFragmentManager.primaryNavigationFragment is PantryListFragment) {
            val intent = Intent(this, GlossarySearchActivity::class.java)
            intent.putExtra("isPantry", true)
            startActivity(intent)
        } else {
            val intent = Intent(this, GlossarySearchActivity::class.java)
            intent.putExtra("isPantry", false)
            startActivity(intent)
        }
    }

    override fun onRecipeItemClicked(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra("SELECTED_RECIPE", recipe)
        startActivity(intent)
    }


    override fun onShoppingItemClicked(ing: IngredientInstance) {
        Log.i("patrin", "Item is now checked!")
    }

    override fun onPantryItemClicked(ing: IngredientInstance) {
        val intent = Intent(this, IngredientDetailActivity::class.java)
        intent.putExtra("SELECTED_ING", ing)
        intent.putExtra("TEST_TYPE", IngredientType(1, "Ground Beef", "imgpath"))
        startActivity(intent)
    }
}

interface OnRecipeClickListener {
    fun onRecipeItemClicked(recipe: Recipe)
}

interface OnPantryClickListener {
    fun onPantryItemClicked(ing: IngredientInstance)
}

interface OnShoppingClickListener {
    fun onShoppingItemClicked(ing: IngredientInstance)
}

