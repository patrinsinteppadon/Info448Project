package com.project.mypantry.viewModels

import androidx.lifecycle.ViewModel
import com.project.mypantry.application.GlossaryManager
import com.project.mypantry.application.PantryApp
import com.project.mypantry.application.PantryListManager

class IngredientDetailViewModel: ViewModel() {

    lateinit var glossaryManager: GlossaryManager
    lateinit var pantryListManager: PantryListManager

    fun init (gManager: GlossaryManager, pManager: PantryListManager){
        glossaryManager = gManager
        pantryListManager = pManager
    }

}