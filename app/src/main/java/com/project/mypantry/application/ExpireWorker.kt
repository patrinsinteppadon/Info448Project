package com.project.mypantry.application

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.*

class ExpireWorker(context: Context, workParams: WorkerParameters) : Worker(context, workParams) {
    private val notificationManager = (context as PantryApp).notificationManager
    private val pantryManager = (context as PantryApp).pantryManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        if (pantryManager.aboutToExpire().isNotEmpty()) {
            notificationManager.notifyUser()
        }

        return Result.success()
    }
}