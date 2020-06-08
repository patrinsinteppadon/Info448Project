package com.project.mypantry.application

import android.content.Context
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Phase 2: Let's worry about notifications and stuff later
 */
class ExpireWorkManager(context: Context) {
    private var workManager = WorkManager.getInstance(context)
    private var lastWorkerId: UUID? = null

    companion object {
        const val WORKER_TAG = "famieisthebestest"
    }

    fun startWork() {
        if (workerRunning()) {
            return
        }

        val constraints = Constraints.Builder()
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ExpireWorker>(14, TimeUnit.HOURS)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(WORKER_TAG)
            .build()


        workManager.enqueue(workRequest)
    }

    private fun workerRunning(): Boolean {
        lastWorkerId?.let {
            return( workManager.getWorkInfoById(it).get().state == WorkInfo.State.ENQUEUED ||
                    workManager.getWorkInfoById(it)
                        .get().state == WorkInfo.State.RUNNING)
        }

        return false
    }

    fun stopAllWork() {
        workManager.cancelAllWorkByTag(WORKER_TAG)
        val x = workManager.getWorkInfosByTag(WORKER_TAG)
    }
}