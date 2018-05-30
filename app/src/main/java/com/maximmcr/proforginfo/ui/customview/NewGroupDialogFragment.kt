package com.maximmcr.proforginfo.ui.customview

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.maximmcr.proforginfo.R
import com.maximmcr.proforginfo.data.foreign.model.Group
import kotlinx.android.synthetic.main.dialog_newgroup.view.*

class NewGroupDialogFragment: DialogFragment() {

    lateinit var onSuccess: (group: Group) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = AlertDialog.Builder(activity).apply {
        val dialogView = activity.layoutInflater.inflate(R.layout.dialog_newgroup, null)
        with(dialogView) {
            setView(this)
            setTitle(R.string.title_dialog_newgroup)
            setNegativeButton(R.string.cancel) {
                dialog, which -> dialog.cancel()
            }
            setPositiveButton(R.string.ok) {dialog, which ->
                val group = Group(
                        newgroup_name.editText!!.text.toString(),
                        newgroup_payment.editText!!.text.toString(),
                        newgroup_additional.editText!!.text.toString()
                )
                onSuccess(group)
            }
        }
    }.create()
}