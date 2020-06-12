package com.project.mypantry.managers

import com.project.mypantry.objects.IngredientType

interface GlossaryManager {
    var glossary: List<IngredientType>

    fun search(regex: String): List<IngredientType>

    fun getIngredientType(id: Int): IngredientType?
}