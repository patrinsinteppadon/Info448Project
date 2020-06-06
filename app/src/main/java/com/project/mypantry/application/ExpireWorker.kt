package com.project.mypantry.application

import android.content.Context
import androidx.work.*

class ExpireWorker(context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    private val notificationManager = (context as PantryApp).notificationManager
    private val pantryManager = (context as PantryApp).pantryManager

    override fun doWork(): Result {
        if (pantryManager.aboutToExpire().isNotEmpty()) {
            notificationManager.notifyUser()
        }

        return Result.success()
    }
}