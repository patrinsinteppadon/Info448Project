package com.project.mypantry.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.mypantry.application.GlossaryManager
import com.project.mypantry.application.PantryApp
import com.project.mypantry.application.PantryListManager
import java.time.LocalDate

class IngredientDetailViewModel: ViewModel() {

    // live data
    var expirationDate = MutableLiveData<LocalDate>()


    // managers
    lateinit var glossaryManager: GlossaryManager
    lateinit var pantryListManager: PantryListManager

    fun init (gManager: GlossaryManager, pManager: PantryListManager){
        glossaryManager = gManager
        pantryListManager = pManager
    }

    fun changeDate(date:LocalDate) {
        expirationDate.value = date
    }

}