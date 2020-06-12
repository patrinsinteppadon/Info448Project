package com.project.mypantry.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.project.mypantry.activity.OnShoppingClickListener
import com.project.mypantry.R
import com.project.mypantry.adapters.ShoppingListAdapter
import com.project.mypantry.application.PantryApp
import com.project.mypantry.managers.ShoppingListManager
import com.project.mypantry.objects.IngredientType
import kotlinx.android.synthetic.main.fragment_grocery_list.*

class ShoppingListFragment : Fragment() {
    private lateinit var shoppingManager: ShoppingListManager
    private lateinit var adapter: ShoppingListAdapter
    private lateinit var contextImg: Context
    private var onGrocerySelectedListener: OnShoppingClickListener? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        contextImg = context
        shoppingManager = (context.applicationContext as PantryApp).shoppingListManager

        if (context is OnShoppingClickListener) {
            onGrocerySelectedListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_grocery_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        adapter = ShoppingListAdapter(shoppingManager, contextImg)

        rvShoppingList.adapter = adapter

        adapter.onGroceryClicked = { ing: IngredientType ->
            onGrocerySelectedListener?.onShoppingItemClicked(ing)
        }

    }

    fun updateAdapter() {
        adapter.update(shoppingManager.shoppingList)
    }

    companion object {
        val TAG: String = ShoppingListFragment::class.java.simpleName
    }
}