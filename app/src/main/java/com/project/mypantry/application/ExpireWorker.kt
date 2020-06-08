package com.project.mypantry.application

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.work.*
import java.time.Duration
import java.time.LocalDate
import java.time.temporal.TemporalAmount

class ExpireWorker(context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    private val notificationManager = (context as PantryApp).notificationManager
    private val pantryManager = (context as PantryApp).pantryManager
    private val glossaryManager = (context as PantryApp).glossaryManager


    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        val x = pantryManager.aboutToExpire()
        for (ing in  pantryManager.aboutToExpire()) {
//            notificationManager.notifyUser(Duration.between(LocalDate.now().atStartOfDay(), ing.expiration.atStartOfDay()).toDays(),
//                glossaryManager.getIngredientType(ing.ingredientID)?.ingredientName?:"")

            Log.i("Tow", "Ingredient Name: ${glossaryManager.getIngredientType(ing.ingredientID)?.ingredientName}, " +
                    "${Duration.between(LocalDate.now().atStartOfDay(), ing.expiration.atStartOfDay()).toDays()} days")
        }

        return Result.success()
    }
}