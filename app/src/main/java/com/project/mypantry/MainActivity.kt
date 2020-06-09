package com.project.mypantry

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.mypantry.GlossarySearchActivity.Companion.FOR_PANTRY
import com.project.mypantry.application.PantryApp
import com.project.mypantry.fragments.ShoppingListFragment
import com.project.mypantry.fragments.PantryListFragment
import com.project.mypantry.fragments.RecipeListFragment
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnRecipeClickListener, OnPantryClickListener, OnShoppingClickListener {
    lateinit var pantryApp: PantryApp
    private var pantryListFrag: PantryListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pantryApp = application as PantryApp
        pantryListFrag = getPantryListFragment()
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
        PantryListFragment.TAG) as? PantryListFragment

    private fun getRecipeListFragment() = supportFragmentManager.findFragmentByTag(
        RecipeListFragment.TAG)

    private fun getGroceryListFragment() = supportFragmentManager.findFragmentByTag(
        ShoppingListFragment.TAG)

    private fun onPantryIconClick() {
        pantryListFrag = getPantryListFragment()
        if (pantryListFrag == null) {
            pantryListFrag = PantryListFragment()
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer, pantryListFrag!!, PantryListFragment.TAG)
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
        Log.i("patrin", "Tabbing to ShoppingList")
        var groceryListFragment = getGroceryListFragment()
        if (groceryListFragment == null) {
            groceryListFragment =
                ShoppingListFragment()
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer, groceryListFragment, ShoppingListFragment.TAG)
                .addToBackStack(ShoppingListFragment.TAG)
                .commit()
        }
    }

    private fun onAddClick() {
        val backStackTop = supportFragmentManager.getBackStackEntryAt(
            supportFragmentManager.backStackEntryCount - 1
        )
        val lastFragmentName = backStackTop.name


        if (lastFragmentName == RecipeListFragment.TAG) {
//            val intent = Intent(this, RecipeDetailActivity::class.java)
//            intent.putExtra("isAdding", true)
//            startActivity(intent)
        } else if (lastFragmentName == PantryListFragment.TAG) {
            val intent = Intent(this, GlossarySearchActivity::class.java)
            intent.putExtra(FOR_PANTRY, true)
            startActivityForResult(intent, 123)
        } else {
            val intent = Intent(this, GlossarySearchActivity::class.java)
            intent.putExtra(FOR_PANTRY, false)
            startActivity(intent)
        }
    }

    override fun onRecipeItemClicked(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra("SELECTED_RECIPE", recipe)
        startActivity(intent)
    }

    // TODO: Along with this, will probably need to include a listener for when the checkbox was clicked
    override fun onShoppingItemClicked(ing: IngredientType) {
        Log.i("patrin", "Item has been clicked!")
    }

    override fun onPantryItemClicked(ing: IngredientInstance) {
        val intent = Intent(this, IngredientDetailActivity::class.java)
        intent.putExtra("SELECTED_ING", ing)
        //intent.putExtra("TEST_TYPE", IngredientType(1, "Ground Beef", "imgpath"))
        startActivityForResult(intent, 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                pantryListFrag?.updateAdapter()
            } else {
                Log.i("Tow", "no result")
            }
        }
    }
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

