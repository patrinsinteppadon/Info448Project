package com.project.mypantry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import com.project.mypantry.application.PantryApp
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import kotlinx.android.synthetic.main.activity_ingredient_detail.*
import java.util.*

class IngredientDetailActivity : AppCompatActivity() {

    companion object {
        const val ING_INST_EXTRA = "ifyoungmetrodonttrussu"
        const val ING_TYPE_EXTRA = "runitupturbo"
        const val NEW_ING_TAG = "qwefiopjvdwef"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_detail)
        title = "Ingredient Detail"

        val glossaryManager = (applicationContext as PantryApp).glossaryManager
        val pantryListManager = (applicationContext as PantryApp).pantryManager
        val ingredientInstance = intent.getParcelableExtra<IngredientInstance>(ING_INST_EXTRA)
        val ingredientType: IngredientType?


        if (ingredientInstance != null) {
            ingredientType = glossaryManager.getIngredientType(ingredientInstance.ingredientID)
            etAmount.setText(ingredientInstance.amount.toString(), TextView.BufferType.EDITABLE)
            etUnit.setText(ingredientInstance.unit, TextView.BufferType.EDITABLE)

            // setting onclick listner to update pantry
            btnSave.setOnClickListener {
                pantryListManager.updateInstance(ingredientInstance.instanceID, ingredientInstance)
                finish()
            }

        } else {
            // else we get ingredient type
            ingredientType = intent.getParcelableExtra<IngredientType>(ING_TYPE_EXTRA)

            // setting onclick listener for new in pantry
            btnSave.setOnClickListener {
                pantryListManager.add(IngredientInstance(pantryListManager.getPantryList().size, ingredientType.id,
                    etAmount.text.toString().toInt(),
                    etUnit.text.toString(), Date()))

                finish()
            }

        }


        // sets ingredient type
        if (ingredientType != null) {
            tvImagePlaceholder.text = ingredientType.ingredientImg
            tvIngredientType.text = ingredientType.ingredientName
        }


    }

}