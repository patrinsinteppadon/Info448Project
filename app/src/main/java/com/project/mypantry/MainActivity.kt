package com.project.mypantry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mypantry.application.PantryApp

class MainActivity : AppCompatActivity() {
    lateinit var pantryApp: PantryApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pantryApp = application as PantryApp
    }
}
