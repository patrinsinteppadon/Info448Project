package com.project.mypantry.managers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
<<<<<<< HEAD:app/src/main/java/com/project/mypantry/application/WorkManager.kt
import java.util.*
=======
import com.project.mypantry.application.ExpireWorker
>>>>>>> tomdev:app/src/main/java/com/project/mypantry/managers/ExpireWorkManager.kt
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

<<<<<<< HEAD:app/src/main/java/com/project/mypantry/application/WorkManager.kt

        workManager.enqueue(workRequest)
=======
        workManager.enqueueUniquePeriodicWork("StartNotifications", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }

    fun stopWork() {
        workManager.cancelAllWork()
>>>>>>> tomdev:app/src/main/java/com/project/mypantry/managers/ExpireWorkManager.kt
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
    }
}