package com.project.mypantry.application

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.project.mypantry.OnRecipeClickListener
import com.project.mypantry.OnShoppingClickListener
import com.project.mypantry.R
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import java.time.LocalDate

// TODO: Convert recipesAll into the list from shoppingListManager
// maybe this fragment can initialize with shoppingListManager.shoppingList
// as an argument? If that gives us the same instance of the list (rather than a
// copy of it), then we'd have access to an updated list
class ShoppingListFragment: Fragment() {
    private var groceriesAll: MutableList<IngredientInstance> = mutableListOf()
    private var onGrocerySelectedListener: OnShoppingClickListener? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("patrin", "Check out this ShoppingList frag")

        // Making test data to see if the view works
        var testIng = IngredientInstance(1,1, 20, "lbs", LocalDate.of(1999, 7, 4))
        var testIng2 = IngredientInstance(2,2, 20, "onions", LocalDate.of(1999, 7, 4))
        var testIng3 = IngredientInstance(3,3, 20, "oz", LocalDate.of(1999, 7, 4))
        groceriesAll.add(testIng)
        groceriesAll.add(testIng2)
        groceriesAll.add(testIng3)

        var testItem = IngredientType(1000, "Ham", "@tools:sample/backgrounds/scenic")
        var testItem2 = IngredientType(2000, "Cheese", "@tools:sample/backgrounds/scenic")
        var testItem3 = IngredientType(3000, "Dairy", "@tools:sample/backgrounds/scenic")

//        arguments?.let { args ->
//            val recipesAll = args.getParcelableArrayList<Recipe>(RECIPEs_KEY)
//            if (recipesAll != null) {
//                this.recipesAll = recipesAll
//            }
//        }

    }

    //TODO: make MainActivity an interface called OnShoppingListClickListener
    // this will implement an onClickListener for this fragment. I think?
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnShoppingClickListener) {
            onGrocerySelectedListener = context
        }
    }

    // generic fragment code
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateListViews()
    }

    private fun updateListViews() {
        groceriesAll?.let {
            val shoppingAdapter = ShoppingListAdapter(it)
            rvRecipeList.adapter = shoppingAdapter

            shoppingAdapter.onGroceryClicked = { ing: IngredientInstance ->
                onGrocerySelectedListener?.onShoppingItemClicked(ing)
            }
        }
    }

    companion object {
        // Keys for intents
        val TAG: String = ShoppingListFragment::class.java.simpleName
        const val RECIPEs_KEY = "RECIPEs_KEY"
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}

//interface OnRecipeSelectedListener {
//    fun onRecipeSelected(recipe: Recipe)
//}