package com.project.mypantry.application

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.project.mypantry.managers.*
import java.lang.ref.WeakReference

class PantryApp : Application() {
    lateinit var pantryManager: PantryListManagerStatic
    lateinit var recipeListManager: RecipeListManager
    lateinit var shoppingListManager: ShoppingListManager
    lateinit var glossaryManager: GlossaryManager
    lateinit var workManager: ExpireWorkManager
    lateinit var notificationManager: MessageNotificationManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        initManagers()
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initManagers() {
        pantryManager = PantryListManagerStatic(
            this
        )
        recipeListManager = RecipeListManagerStatic(
            this
        )
        shoppingListManager = ShoppingListManagerStatic(
            this
        )
        glossaryManager = GlossaryManagerStatic(
            this
        )
        workManager = ExpireWorkManager(this)
        notificationManager = MessageNotificationManager(this)
    }

    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        private var activityReferences = 0
        private var isActivityConfigurationChanging = false
        private var weakActivity = WeakReference<Activity?>(null)
        var isAppForegrounded = false

        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStarted(activity: Activity) {
            if (++activityReferences == 1 && !isActivityConfigurationChanging) {
                // app enters foreground
                isAppForegrounded = true
                workManager.stopWork()
            }
            weakActivity = WeakReference(activity)
        }

        override fun onActivityDestroyed(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        override fun onActivityStopped(activity: Activity) {
            isActivityConfigurationChanging = activity.isChangingConfigurations
            if (--activityReferences == 0 && !isActivityConfigurationChanging) {
                // App enters background
                isAppForegrounded = false
                workManager.startWork()
            }
            weakActivity.clear()
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
        override fun onActivityResumed(activity: Activity) {}
    }
}