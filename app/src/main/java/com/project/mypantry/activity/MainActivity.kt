package com.project.mypantry.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.mypantry.activity.GlossarySearchActivity.Companion.FOR_PANTRY
import com.project.mypantry.R
import android.view.View
import com.project.mypantry.activity.GlossarySearchActivity.Companion.FOR_PANTRY
import com.project.mypantry.activity.IngredientDetailActivity.Companion.FOR_SHOPPING
import com.project.mypantry.activity.IngredientDetailActivity.Companion.ING_INST_EXTRA
import com.project.mypantry.activity.IngredientDetailActivity.Companion.ING_TYPE_EXTRA
import com.project.mypantry.application.PantryApp
import com.project.mypantry.fragments.ShoppingListFragment
import com.project.mypantry.fragments.PantryListFragment
import com.project.mypantry.fragments.RecipeListFragment
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    OnRecipeClickListener,
    OnPantryClickListener,
    OnShoppingClickListener {
    lateinit var pantryApp: PantryApp
    private var pantryListFrag: PantryListFragment? = null
    private var shoppingListFrag: ShoppingListFragment? =  null

    companion object {
        const val EDIT_PANTRY_RC = 123
        const val ADD_SHOPPING_RC = 234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        pantryApp = application as PantryApp
        pantryListFrag = getPantryListFragment()
        shoppingListFrag = getGroceryListFragment()
        if (savedInstanceState == null) {

            onPantryIconClick()
        }

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

        clearButton.setOnClickListener {
            pantryApp.shoppingListManager.clearList()
            shoppingListFrag?.updateAdapter()
        }
    }

    private fun getPantryListFragment() = supportFragmentManager.findFragmentByTag(
        PantryListFragment.TAG) as? PantryListFragment

    private fun getRecipeListFragment() = supportFragmentManager.findFragmentByTag(
        RecipeListFragment.TAG)

    private fun getGroceryListFragment() = supportFragmentManager.findFragmentByTag(
        ShoppingListFragment.TAG) as? ShoppingListFragment

    private fun onPantryIconClick() {
        title = "My Pantry"
        pantryListFrag = getPantryListFragment()
        if (pantryListFrag == null) {
            pantryListFrag = PantryListFragment()
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer, pantryListFrag!!, PantryListFragment.TAG)
                .addToBackStack(PantryListFragment.TAG)
                .commit()
        }
        llButtons.visibility = View.VISIBLE
        clearButton.visibility = View.GONE
        addButton.visibility = View.VISIBLE
    }

    private fun onRecipeIconClick() {
        llButtons.visibility = View.GONE
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
        title = "Shopping List"
        shoppingListFrag = getGroceryListFragment()
        if (shoppingListFrag == null) {
            shoppingListFrag = ShoppingListFragment()
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer, shoppingListFrag!!, ShoppingListFragment.TAG)
                .addToBackStack(ShoppingListFragment.TAG)
                .commit()
        }
        llButtons.visibility = View.VISIBLE
        clearButton.visibility = View.VISIBLE
        addButton.visibility = View.VISIBLE
    }

    private fun onAddClick() {
        val backStackTop = supportFragmentManager.getBackStackEntryAt(
            supportFragmentManager.backStackEntryCount - 1
        )
        val lastFragmentName = backStackTop.name

        if (lastFragmentName == PantryListFragment.TAG) {
            val intent = Intent(this, GlossarySearchActivity::class.java)
            intent.putExtra(GlossarySearchActivity.FOR_PANTRY, true)
            startActivityForResult(intent, EDIT_PANTRY_RC)
        } else {
            val intent = Intent(this, GlossarySearchActivity::class.java)
            intent.putExtra(GlossarySearchActivity.FOR_PANTRY, false)
            startActivityForResult(intent, ADD_SHOPPING_RC)
        }
    }

    override fun onRecipeItemClicked(recipe: Recipe) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.link))
        startActivity(intent)
    }

    override fun onShoppingItemClicked(ing: IngredientType) {
        val intent = Intent(this, IngredientDetailActivity::class.java)
        intent.putExtra(ING_TYPE_EXTRA, ing)
        intent.putExtra(FOR_SHOPPING, true)
        startActivityForResult(intent, 345)
    }

    override fun onPantryItemClicked(ing: IngredientInstance) {
        Log.i("LocalDate", ing.expiration.toString())
        val intent = Intent(this, IngredientDetailActivity::class.java)
        intent.putExtra(ING_INST_EXTRA, ing)
        startActivityForResult(intent, EDIT_PANTRY_RC)
    }

    override fun onBackPressed() {
        // if there's more than 1 stack
        // else just finish the activity
        if (supportFragmentManager.backStackEntryCount > 1) {
            onSupportNavigateUp()
        } else {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_PANTRY_RC) {
            if (resultCode == Activity.RESULT_OK) {
                pantryListFrag?.updateAdapter()
            }
        } else {
                // change shopping
                if (resultCode == Activity.RESULT_OK) {
                    shoppingListFrag?.updateAdapter()
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

