package com.maximmcr.proforginfo.ui.customview

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import java.util.*
import android.view.View
import com.maximmcr.proforginfo.data.local.model.formatDate


class MonthSelectorDialog : DialogFragment(), DatePickerDialog.OnDateSetListener {

    interface OnDateSelected {
        fun onDateSelected(date: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = with(Calendar.getInstance()) {
        return object : DatePickerDialog(
                context,
                AlertDialog.THEME_HOLO_DARK,
                this@MonthSelectorDialog,
                get(Calendar.YEAR),
                get(Calendar.MONTH),
                get(Calendar.DAY_OF_MONTH)
        ) {
            override fun onCreate(savedInstanceState: Bundle) {
                super.onCreate(savedInstanceState)
                val day = context.resources.getIdentifier("android:id/day", null, null)
                if (day != 0) {
                    val dayPicker = findViewById<View>(day)
                    if (dayPicker != null) {
                        dayPicker.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        if (activity is OnDateSelected) (activity as OnDateSelected).onDateSelected(formatDate(year, month))
    }
}
