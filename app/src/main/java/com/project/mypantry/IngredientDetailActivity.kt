package com.project.mypantry

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.project.mypantry.application.PantryApp
import com.project.mypantry.fragments.DatePickerFragment
import com.project.mypantry.fragments.SetDateListener
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.viewModels.IngredientDetailViewModel
import kotlinx.android.synthetic.main.activity_ingredient_detail.*
import java.time.LocalDate
import java.util.*

class IngredientDetailActivity : AppCompatActivity(), SetDateListener {


    companion object {
        const val ING_INST_EXTRA = "ifyoungmetrodonttrussu"
        const val ING_TYPE_EXTRA = "runitupturbo"
        const val NEW_ING_TAG = "qwefiopjvdwef"
    }

    var theDate: LocalDate? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_detail)
        title = "Ingredient Detail"

        val glossaryManager = (applicationContext as PantryApp).glossaryManager
        val pantryListManager = (applicationContext as PantryApp).pantryManager


        val ingredientInstance = intent.getParcelableExtra<IngredientInstance>(ING_INST_EXTRA)
        val ingredientType: IngredientType?

        // i.e. if its not a new item, we have an existing ingredient ID
        if (ingredientInstance != null) {
            ingredientType = glossaryManager.getIngredientType(ingredientInstance.ingredientID)
            etAmount.setText(ingredientInstance.amount.toString(), TextView.BufferType.EDITABLE)
            etUnit.setText(ingredientInstance.unit, TextView.BufferType.EDITABLE)
            theDate = ingredientInstance.expiration
            btnExpirationDate.text = "EXP: ${theDate.toString()}"

            // setting onclick listener to update pantry
            btnSave.setOnClickListener {
                pantryListManager.updateInstance(ingredientInstance.instanceID, ingredientInstance)
                finish()
            }

        } else {
            // else we get ingredient
            ingredientType = intent.getParcelableExtra<IngredientType>(ING_TYPE_EXTRA)

            // setting onclick listener for new in pantry
            btnSave.setOnClickListener {
                pantryListManager.add(IngredientInstance(123, ingredientType.id,
                    etAmount.text.toString().toInt(),
                    etUnit.text.toString(), LocalDate.of(2,1,2)))

                finish()
            }

        }


        // sets ingredient type
        if (ingredientType != null) {
            tvImagePlaceholder.text = ingredientType.ingredientImg
            tvIngredientType.text = ingredientType.ingredientName
        }

        btnExpirationDate.setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(supportFragmentManager, "datePicker")
        }


    }

    override fun onDateSelected(date: LocalDate) {
        theDate = date
        btnExpirationDate.text = "EXP: ${theDate.toString()}"
    }

}