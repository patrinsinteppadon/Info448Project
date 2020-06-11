package com.project.mypantry.managers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.project.mypantry.R
import com.project.mypantry.activity.MainActivity
import com.project.mypantry.activity.TowsMain

/**
 * Phase 2: Let's worry about notifications and stuff later
 */
class MessageNotificationManager(private val context: Context) {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    fun notifyUser(daysTillExpire: Long, ingredientName: String, instanceID: Int) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, "MESSAGE_CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Food is expiring")
            .setContentText("Your food is about to expire! Check on the food in your pantry!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(instanceID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Smart Pantry"
            val descriptionText = "Keeps track of your groceries"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("MESSAGE_CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

}