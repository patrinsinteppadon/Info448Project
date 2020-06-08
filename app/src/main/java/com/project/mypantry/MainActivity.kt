package com.project.mypantry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.mypantry.application.PantryApp
import com.project.mypantry.application.RecipeListFragment
import com.project.mypantry.application.ShoppingListFragment
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnRecipeClickListener, OnShoppingClickListener {
    lateinit var pantryApp: PantryApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pantryApp = application as PantryApp

        recipeButton.setOnClickListener {
            onRecipeIconClick()
        }
    }

//    private fun getPantryListFragment() = supportFragmentManager.findFragmentByTag(PantryListFragment.TAG)

    private fun getRecipeListFragment() = supportFragmentManager.findFragmentByTag(RecipeListFragment.TAG)

//    private fun getGroceryListFragment() = supportFragmentManager.findFragmentByTag(GroceryListFragment.TAG)

//    private fun onPantryIconClick() {
//        var pantryListFragment = getPantryListFragment()
//        if (pantryListFragment == null) {
//            pantryListFragment = PantryListFragment()
//            supportFragmentManager.popBackStack()
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragContainer, pantryListFragment, PantryListFragment.TAG)
//                .addToBackStack(PantryListFragment.TAG)
//                .commit()
//        }
//    }

    private fun onRecipeIconClick() {
        var recipeListFragment = getRecipeListFragment()
        if (recipeListFragment == null) {
            recipeListFragment = RecipeListFragment()
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer, recipeListFragment, RecipeListFragment.TAG)
                .addToBackStack(RecipeListFragment.TAG)
                .commit()
        }
    }

//    private fun onGroceryIconClick() {
//        var groceryListFragment = getGroceryListFragment()
//        if (groceryListFragment == null) {
//            groceryListFragment = GroceryListFragment()
//            supportFragmentManager.popBackStack()
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragContainer, groceryListFragment, GroceryListFragment.TAG)
//                .addToBackStack(GroceryListFragment.TAG)
//                .commit()
//        }
//    }
//
//    private fun onAddClick() {
//        if (supportFragmentManager.primaryNavigationFragment is RecipeListFragment) {
//            val intent = Intent(this, RecipeDetailActivity::class.java)
//            intent.putExtra("isAdding", true)
//            startActivity(intent)
//        } else {
//            val intent = Intent(this, IngredientDetailActivity::class.java)
//            intent.putExtra("isAdding", true)
//            startActivity(intent)
//        }
//    }

    override fun onRecipeItemClicked(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra("SELECTED_RECIPE", recipe)
        startActivity(intent)
    }

    override fun onShoppingItemClicked(ing: IngredientType) {
        Log.i("patrin", "Item is now checked!")
    }

//    override fun onPantryItemClicked(ing: IngredientInstance) {
//        val intent = Intent(this, PantryDetailActivity::class.java)
//        intent.putExtra("SELECTED_ING", ing)
//        startActivity(intent)
//    }
}

interface OnRecipeClickListener {
    fun onRecipeItemClicked(recipe: Recipe)
}

interface OnPantryClickListener {
    fun onPantryItemClicked(ing: IngredientInstance)
}

interface OnShoppingClickListener {
    fun onShoppingItemClicked(ing: IngredientType)
}

