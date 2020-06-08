package com.project.mypantry.managers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.project.mypantry.application.ExpireWorker
import java.util.concurrent.TimeUnit

/**
 * Phase 2: Let's worry about notifications and stuff later
 */
class ExpireWorkManager(context: Context) {
    private var workManager = WorkManager.getInstance(context)

    fun startWork() {
        val constraints = Constraints.Builder()
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ExpireWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork("StartNotifications", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }

    fun stopWork() {
        workManager.cancelAllWork()
    }
}