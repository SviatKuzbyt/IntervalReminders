package ua.sviatkuzbyt.intervalreminders.ui.elements

import android.content.Context
import androidx.appcompat.app.AlertDialog
import ua.sviatkuzbyt.intervalreminders.R

class ConfirmRemoveDialog(action: () -> Unit, context: Context) {
    private val builder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.confirm_window)
    private var dialog: AlertDialog

    init {
        builder
            .setTitle(R.string.delete)
            .setMessage(R.string.delete_description)
            .setPositiveButton(R.string.yes) { _, _ ->
                action.invoke()
            }
            .setNegativeButton(R.string.no) { d, _ ->
                d.cancel()
            }

        dialog = builder.create()
    }

    fun showWindow(){ dialog.show() }
}