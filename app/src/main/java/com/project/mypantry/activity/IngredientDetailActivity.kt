package com.project.mypantry.activity

import android.app.Activity
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
import com.project.mypantry.R
import com.project.mypantry.application.PantryApp
import com.project.mypantry.fragments.DatePickerFragment
import com.project.mypantry.fragments.SetDateListener
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.viewModels.IngredientDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_ingredient_detail.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class IngredientDetailActivity : AppCompatActivity(), SetDateListener {

    companion object {
        const val ING_INST_EXTRA = "ingredient_instance"
        const val ING_TYPE_EXTRA = "ingredient_type"
        const val FOR_SHOPPING = "shopping"
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
        val shoppingListManager = (applicationContext as PantryApp).shoppingListManager


        val ingredientInstance = intent.getParcelableExtra<IngredientInstance>(ING_INST_EXTRA)
        val ingredientType: IngredientType?

        // getting ingredient type
        if (ingredientInstance != null) {
            ingredientType = glossaryManager.getIngredientType(ingredientInstance.ingredientID)
            // delete button
            btnDelete.visibility = View.VISIBLE
            btnDelete.setOnClickListener {
                ingredientDetailViewModel.delete()
                saveChange()
            }
        } else {
            btnSave.text = "Add To Pantry"
            ingredientType = intent.getParcelableExtra<IngredientType>(ING_TYPE_EXTRA)
        }

        // init view model to handle rotation
        if (savedInstanceState != null) {
            ingredientDetailViewModel.init(
                glossaryManager,
                pantryListManager, shoppingListManager, ingredientType!!, ingredientInstance, false
            )
        } else {
            ingredientDetailViewModel.init(
                glossaryManager, pantryListManager, shoppingListManager,
                ingredientType!!, ingredientInstance, true
            )
        }

        // set concrete values: Ingredient Name and Photo from ingredientType
        tvIngredientType.text = ingredientType.ingredientName
        var localImgLink =
            this.resources.getIdentifier(ingredientType.ingredientImg, "drawable", this.packageName)
        Picasso.get().load(localImgLink)
            .placeholder(R.drawable.ic_launcher_background)
            .into(ivIngImg);

        // observe amount, unit and expiration date
        ingredientDetailViewModel.theAmount.observe(this) {
            tvTheAmount.text = it.toString()
        }

        ingredientDetailViewModel.theDate.observe(this) {
            if (it != null) {
                val dateString = it.format(DateTimeFormatter.ofPattern("dd LLLL"))
                btnExpirationDate.text = "Best Before: $dateString"
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

        etUnit.addTextChangedListener(object : TextWatcher {
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
            if (intent.getBooleanExtra(FOR_SHOPPING, false)) {
                ingredientDetailViewModel.deleteFromShopping()
            }
            ingredientDetailViewModel.save()
            saveChange()
        }

        ibAddAmount.setOnClickListener {
            ingredientDetailViewModel.addAmount()
        }

        ibSubtractAmount.setOnClickListener {
            ingredientDetailViewModel.subtractAmount()
        }

    }

    private fun saveChange() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onDateSelected(date: LocalDate) {
        ingredientDetailViewModel.changeDate(date)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}