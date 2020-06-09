package com.project.mypantry.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.project.mypantry.activity.OnPantryClickListener
import com.project.mypantry.R
import com.project.mypantry.application.PantryApp
import com.project.mypantry.adapters.PantryListAdapter
import com.project.mypantry.objects.IngredientInstance
import kotlinx.android.synthetic.main.fragment_pantry_list.*


class PantryListFragment(): Fragment() {
        //private var ingAll: MutableList<IngredientInstance> = mutableListOf()
        private var onPantryClickedListener: OnPantryClickListener? = null
        lateinit var adapter: PantryListAdapter
        lateinit var pantryApp: PantryApp


        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            if (context is OnPantryClickListener) {
                onPantryClickedListener = context
                pantryApp = (context.applicationContext as PantryApp)
            }



        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return layoutInflater.inflate(R.layout.fragment_pantry_list, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setPantryAdapter()
        }

        private fun setPantryAdapter() {
            adapter = PantryListAdapter(pantryApp.pantryManager.pantry, pantryApp)

            pantryList.adapter = adapter

            adapter.onPantryClicked = { ing: IngredientInstance ->
                onPantryClickedListener?.onPantryItemClicked(ing)
            }
        }

    fun updateAdapter() {
        adapter.update(pantryApp.pantryManager.getPantryList())
    }



        companion object {
            val TAG: String = PantryListFragment::class.java.simpleName
        }
}