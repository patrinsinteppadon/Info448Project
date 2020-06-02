package com.project.mypantry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.mypantry.application.PantryApp
import com.project.mypantry.application.PantryListManager
import com.project.mypantry.objects.IngredientInstance
import kotlinx.android.synthetic.main.activity_tows_main.*
import java.util.*
import kotlin.random.Random

class TowsMain : AppCompatActivity() {
    private lateinit var pantryApp: PantryApp
    private lateinit var pantryListManager: PantryListManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tows_main)

        // init pantryApp and managers
        pantryApp = (application as PantryApp)



        btn1.setOnClickListener {
            pantryApp.pantryManager.add(IngredientInstance(Random.nextInt(), Random.nextInt(), 5, "LBS", Date()))
        }

        btn2.setOnClickListener {
            Log.i("Tow", pantryApp.pantryManager.getPantryList().toString())
        }
    }
}
