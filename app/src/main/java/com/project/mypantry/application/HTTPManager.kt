package com.project.mypantry.application

/**
 * responseAsObject will be null until the results are loaded in
 */
interface HTTPManager {
    var responseAsObject: JsonResponse?

    fun downloadLists()
}