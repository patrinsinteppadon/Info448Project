package com.project.mypantry.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private var setDateListener: SetDateListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (context is SetDateListener) {
            setDateListener = context as SetDateListener
        }

        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        setDateListener?.onDateSelected(LocalDate.of(year, month + 1, day))
    }
}

interface SetDateListener {
    fun onDateSelected(date: LocalDate)
}