package com.project.mypantry.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.project.mypantry.objects.IngredientType

class GlossaryListAdapter(private val context: Context,
private var glossary: List<IngredientType>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        val ingredientName = rowView.findViewById(android.R.id.text1) as TextView
        val ingredientType = getItem(position) as IngredientType
        ingredientName.text = ingredientType.ingredientName
        return  rowView
    }

    override fun getItem(position: Int): Any {
        return glossary[position]
    }

    override fun getItemId(position: Int): Long {
        return glossary[position].id.toLong()
    }

    override fun getCount(): Int {
        return glossary.size
    }

    fun updateGlossaryList(newList: List<IngredientType>) {
        glossary = newList
        notifyDataSetChanged()
    }

}