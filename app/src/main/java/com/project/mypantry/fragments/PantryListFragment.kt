package com.project.mypantry.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.project.mypantry.OnPantryClickListener
import com.project.mypantry.R
import com.project.mypantry.application.PantryApp
import com.project.mypantry.application.PantryListAdapter
import com.project.mypantry.managers.PantryListManager
import com.project.mypantry.objects.IngredientInstance
import kotlinx.android.synthetic.main.fragment_pantry_list.*

class PantryListFragment(context: Context): Fragment() {
        private var ingAll: MutableList<IngredientInstance> = (context as PantryApp).pantryManager.getPantryList().toMutableList()
        private var onPantryClickedListener: OnPantryClickListener? = null


        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.i("Click", "Going to Recipe")

            // Making test data to see if the view works
//            var testIng = IngredientInstance(1, "Ground Beef",1, 20, "lbs", LocalDate.of(1999, 7, 4))
//            var testIng2 = IngredientInstance(2, "Onion",2, 20, "onions", LocalDate.of(1999, 7, 4))
//            var testIng3 = IngredientInstance(3, "Oregano",3, 20, "oz", LocalDate.of(1999, 7, 4))
//            ingAll.add(testIng)
//            ingAll.add(testIng2)
//            ingAll.add(testIng3)

//        arguments?.let { args ->
//            val recipesAll = args.getParcelableArrayList<Recipe>(RECIPEs_KEY)
//            if (recipesAll != null) {
//                this.recipesAll = recipesAll
//            }
//        }

        }

        override fun onAttach(context: Context) {
            super.onAttach(context)

            if (context is OnPantryClickListener) {
                onPantryClickedListener = context
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
            val pantryAdapter = PantryListAdapter(ingAll)

            pantryList.adapter = pantryAdapter

            pantryAdapter.onPantryClicked = { ing: IngredientInstance ->
                onPantryClickedListener?.onPantryItemClicked(ing)
            }
        }

        companion object {
            val TAG: String = PantryListFragment::class.java.simpleName
        }
}