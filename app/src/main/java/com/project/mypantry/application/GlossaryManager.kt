package com.project.mypantry.application

import com.project.mypantry.objects.IngredientType

/**
 * This is the IngredientDictionary that we talked about in the Miro.
 * Patrin renamed it so that its purpose is a little more explicit.
 */
interface GlossaryManager {
    var glossary: List<IngredientType>

    // note that there's no "add" for our MVP. It creates too many logistics issues
    fun search(regex: String): List<IngredientType>
}