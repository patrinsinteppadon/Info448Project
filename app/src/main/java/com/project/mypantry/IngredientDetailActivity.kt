package com.project.mypantry

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.observe
import com.project.mypantry.application.PantryApp
import com.project.mypantry.fragments.DatePickerFragment
import com.project.mypantry.fragments.SetDateListener
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.viewModels.IngredientDetailViewModel
import kotlinx.android.synthetic.main.activity_ingredient_detail.*
import java.time.LocalDate

class IngredientDetailActivity : AppCompatActivity(), SetDateListener {


    companion object {
        const val ING_INST_EXTRA = "ifyoungmetrodonttrussu"
        const val ING_TYPE_EXTRA = "runitupturbo"
        const val NEW_ING_TAG = "qwefiopjvdwef"
    }

    private val ingredientDetailViewModel: IngredientDetailViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_detail)
        // initial setups
        title = "Ingredient Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val glossaryManager = (applicationContext as PantryApp).glossaryManager
        val pantryListManager = (applicationContext as PantryApp).pantryManager


        val ingredientInstance = intent.getParcelableExtra<IngredientInstance>("SELECTED_ING")
        val ingredientType: IngredientType?

        // getting ingredient type
        if (ingredientInstance != null) {
            ingredientType = glossaryManager.getIngredientType(ingredientInstance.ingredientID)
            // delete button
            btnDelete.visibility = View.VISIBLE
            btnDelete.setOnClickListener {
                ingredientDetailViewModel.delete()
                finish()
            }
        } else {
            ingredientType = intent.getParcelableExtra<IngredientType>(ING_TYPE_EXTRA)
        }

        // init view model to handle rotation
        if (savedInstanceState != null) {
            ingredientDetailViewModel.init(
                glossaryManager,
                pantryListManager, ingredientType!!, ingredientInstance, false
            )
        } else {
            ingredientDetailViewModel.init(
                glossaryManager,
                pantryListManager, ingredientType!!, ingredientInstance, true
            )
        }


        // set concrete values: Ingredient Name and Photo from ingredientType
        tvImagePlaceholder.text = ingredientType.ingredientImg
        tvIngredientType.text = ingredientType.ingredientName

        // observe amount, unit and expiration date
        ingredientDetailViewModel.theAmount.observe(this) {
                tvTheAmount.text = it.toString()
        }

        ingredientDetailViewModel.theDate.observe(this) {
            if (it != null) {
                btnExpirationDate.text = "EXP: ${it.toString()}"
            }
        }

        ingredientDetailViewModel.theUnit.observe(this) {
            if (it != null) {
                etUnit.setText(it, TextView.BufferType.EDITABLE)
            }
        }

        ingredientDetailViewModel.filledOut.observe(this) {
            btnSave.isEnabled = it
        }




        btnExpirationDate.setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(supportFragmentManager, "datePicker")
        }

        etUnit.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    ingredientDetailViewModel.changeUnit(s.toString())
                    etUnit.setSelection(etUnit.length())
                }
            }

        })

        btnSave.setOnClickListener {
            ingredientDetailViewModel.save()
            finish()
        }

        ibAddAmount.setOnClickListener{
            ingredientDetailViewModel.addAmount()
        }

        ibSubtractAmount.setOnClickListener {
            ingredientDetailViewModel.subtractAmount()
        }


    }

    override fun onDateSelected(date: LocalDate) {
        ingredientDetailViewModel.changeDate(date)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}