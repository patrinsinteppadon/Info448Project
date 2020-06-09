package com.project.mypantry.viewModels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.mypantry.managers.GlossaryManager
import com.project.mypantry.managers.PantryListManager
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import java.time.LocalDate

class IngredientDetailViewModel: ViewModel() {

    // live data
    var theDate = MutableLiveData<LocalDate>()
    var theAmount = MutableLiveData<Int>(0)
    var theUnit = MutableLiveData<String>()
    var filledOut = MutableLiveData<Boolean>(false)


    // managers
    private lateinit var glossaryManager: GlossaryManager
    private lateinit var pantryListManager: PantryListManager
    lateinit var ingredientType: IngredientType
    private var ingredientInstance: IngredientInstance? = null

    fun init (gManager: GlossaryManager, pManager: PantryListManager, ingredientType: IngredientType, ingredientInstance: IngredientInstance?,
    reload: Boolean) {
        glossaryManager = gManager
        pantryListManager = pManager
        this.ingredientType = ingredientType
        if (reload) {
            if(ingredientInstance != null) {
                theDate.value = ingredientInstance.expiration
                theAmount.value = ingredientInstance.amount
                theUnit.value = ingredientInstance.unit
                this.ingredientInstance = ingredientInstance
            }
        }
        checkFields()
    }

    fun delete() {
        ingredientInstance?.let{pantryListManager.delete(it.instanceID)}
    }

    private fun checkFields() {
        if(theDate.value == null) {
            filledOut.value = false
            return
        }

        if(theAmount.value == 0) {
            filledOut.value = false
            return
        }

        if(theUnit.value == null) {
            filledOut.value = false
            return
        }

        filledOut.value = true

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun save() {
        if (ingredientInstance != null) {
            Log.i("Save", "It is saving")
            ingredientInstance?.let{pantryListManager.updateInstance(it.instanceID,
                IngredientInstance(it.instanceID, ingredientType.id, theAmount.value!!, theUnit.value!!, theDate.value!!))}
        } else {
            val instanceId = pantryListManager.getSize()
            pantryListManager.add(IngredientInstance(instanceId, ingredientType.id,
                theAmount.value!!, theUnit.value!!, theDate.value!!))
        }
    }

    fun changeDate(date:LocalDate) {
        theDate.value = date
        checkFields()
    }

    fun addAmount() {
        theAmount.value?.let { currentAmount ->
            theAmount.value = currentAmount + 1
        }
        checkFields()
    }

    fun subtractAmount() {
        theAmount.value?.let { currentAmount ->
            if (currentAmount != 0) {
                theAmount.value = currentAmount - 1
            }
        }
        checkFields()
    }

    fun changeUnit(unit:String) {
        if (unit == theUnit.value) {
            return
        }
        if (unit == "") {
            theUnit.value = null
        } else {
            theUnit.value = unit
        }
        checkFields()
    }

    fun print() {
        Log.i("tow1204", "${(theDate.value?.toString())?:"no date"}, ${theAmount.value}, ${theUnit.value?:"no unit"}, filledOut: ${filledOut.value}")
    }

}