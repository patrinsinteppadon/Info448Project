package com.project.mypantry.managers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.project.mypantry.application.ExpireWorker
import java.util.concurrent.TimeUnit

class ExpireWorkManager(context: Context) {
    private var workManager = WorkManager.getInstance(context)

    companion object {
        const val WORKER_TAG = "worker_tag"
    }

    fun startWork() {
        val constraints = Constraints.Builder()
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ExpireWorker>(14, TimeUnit.HOURS)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(WORKER_TAG)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "StartNotifications",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    fun stopWork() {
        workManager.cancelAllWork()
    }
}