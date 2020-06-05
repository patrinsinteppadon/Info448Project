package com.project.mypantry

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.project.mypantry.IngredientDetailActivity.Companion.ING_INST_EXTRA
import com.project.mypantry.IngredientDetailActivity.Companion.ING_TYPE_EXTRA
import com.project.mypantry.application.PantryApp
import com.project.mypantry.application.PantryListManager
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import kotlinx.android.synthetic.main.activity_tows_main.*
import java.time.LocalDate
import java.util.*
import kotlin.random.Random

class TowsMain : FragmentActivity() {
    private lateinit var pantryApp: PantryApp
    private lateinit var pantryListManager: PantryListManager


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tows_main)

        // init pantryApp and managers
        pantryApp = (application as PantryApp)



        btn1.setOnClickListener {
            val intent: Intent = Intent(this, IngredientDetailActivity::class.java).apply {
                putExtra(ING_INST_EXTRA, IngredientInstance(0, 2, 15, "lbs",
                LocalDate.now()
                ))
            }
            startActivity(intent)
        }

        btn2.setOnClickListener {
            Log.i("Tow", pantryApp.pantryManager.getPantryList().toString())
        }
    }
}
