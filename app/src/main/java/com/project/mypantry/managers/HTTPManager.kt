package com.project.mypantry.managers

import com.project.mypantry.managers.JsonResponse

/**
 * responseAsObject will be null until the results are loaded in
 */
interface HTTPManager {
    var responseAsObject: JsonResponse?

    fun downloadLists()
}