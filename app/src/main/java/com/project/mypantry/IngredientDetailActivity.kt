package com.project.mypantry

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.project.mypantry.application.PantryApp
import com.project.mypantry.fragments.DatePickerFragment
import com.project.mypantry.fragments.SetDateListener
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import kotlinx.android.synthetic.main.activity_ingredient_detail.*
import java.time.LocalDate

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
        // initial setups
        title = "Ingredient Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val glossaryManager = (applicationContext as PantryApp).glossaryManager
        val pantryListManager = (applicationContext as PantryApp).pantryManager


        val ingredientInstance = intent.getParcelableExtra<IngredientInstance>(ING_INST_EXTRA)
        val ingredientType: IngredientType?

        // i.e. if its not a new item, we have an existing ingredient ID
        if (ingredientInstance != null) {
            ingredientType = glossaryManager.getIngredientType(ingredientInstance.ingredientID)

            // setting input field and start date
            etAmount.setText(ingredientInstance.amount.toString(), TextView.BufferType.EDITABLE)
            etUnit.setText(ingredientInstance.unit, TextView.BufferType.EDITABLE)
            theDate = ingredientInstance.expiration

            btnExpirationDate.text = "EXP: ${theDate.toString()}"

            // delete button
            btnDelete.visibility = View.VISIBLE
            btnDelete.setOnClickListener {
                pantryListManager.delete(ingredientInstance.instanceID)
                finish()
            }


            // save button
            // setting onclick listener to update pantry
            btnSave.setOnClickListener {
                ingredientInstance.amount = etAmount.text.toString().toInt()
                ingredientInstance.unit = etUnit.text.toString()
                ingredientInstance.expiration = theDate as LocalDate
                pantryListManager.updateInstance(ingredientInstance.instanceID, ingredientInstance)
                finish()
            }

        } else {
            // else we get ingredient
            ingredientType = intent.getParcelableExtra<IngredientType>(ING_TYPE_EXTRA)

            // save button
            // setting onclick listener for saving new instance into pantry
            btnSave.setOnClickListener {
                val instanceId = pantryListManager.getSize()
                pantryListManager.add(IngredientInstance(instanceId, ingredientType.id,
                    etAmount.text.toString().toInt(),
                    etUnit.text.toString(), theDate?: LocalDate.now()))

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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}