package com.project.mypantry.managers

import com.project.mypantry.managers.JsonResponse
import com.project.mypantry.objects.pantryResultsMod

/**
 * responseAsObject will be null until the results are loaded in
 */
interface HTTPManager {
   var pantryResults: pantryResultsMod

    fun downloadLists()
}