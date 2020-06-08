package com.project.mypantry

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.work.WorkManager
import com.project.mypantry.GlossarySearchActivity.Companion.FOR_PANTRY
import com.project.mypantry.IngredientDetailActivity.Companion.ING_INST_EXTRA
import com.project.mypantry.IngredientDetailActivity.Companion.ING_TYPE_EXTRA
import com.project.mypantry.application.ExpireWorkManager
import com.project.mypantry.application.PantryApp
import com.project.mypantry.managers.PantryListManager
import kotlinx.android.synthetic.main.activity_tows_main.*

class TowsMain : FragmentActivity() {
    private lateinit var pantryApp: PantryApp
    private lateinit var pantryListManager: PantryListManager
    private lateinit var workManager: ExpireWorkManager


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tows_main)

        // init pantryApp and managers
        pantryApp = (application as PantryApp)
        pantryListManager = pantryApp.pantryManager
        workManager = pantryApp.workManager


        btn1.setOnClickListener {
            val intent: Intent = Intent(this, IngredientDetailActivity::class.java).apply {
                //putExtra(ING_TYPE_EXTRA, pantryApp.glossaryManager.getIngredientType(1))
                val x = pantryApp.pantryManager.get(pantryApp.pantryManager.getSize() - 1)
                putExtra(ING_INST_EXTRA, x)
            }
            startActivity(intent)
        }

        btn3.setOnClickListener {
            val intent: Intent = Intent(this, GlossarySearchActivity::class.java).apply {
                putExtra(FOR_PANTRY, true)
            }
            startActivity(intent)
        }

        btn2.setOnClickListener {
            Log.i("Tow", pantryApp.pantryManager.getPantryList().toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        workManager.startWork()
    }

    override fun onStart() {
        super.onStart()
        workManager.stopAllWork()
    }
}
