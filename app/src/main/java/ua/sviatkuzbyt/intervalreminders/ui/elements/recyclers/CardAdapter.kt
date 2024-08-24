package ua.sviatkuzbyt.intervalreminders.ui.elements.recyclers

import android.content.Context
import android.util.Log
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.data.elements.CardData
import ua.sviatkuzbyt.intervalreminders.ui.elements.ConfirmRemoveDialog
import ua.sviatkuzbyt.intervalreminders.ui.elements.interfaces.RecyclerAction

class CardAdapter(
    dataSet: MutableList<CardData>,
    action: RecyclerAction,
    context: Context
): RemindAdapter(dataSet, action) {
    private var removePosition = -1
    private var removeId = -1L
    private val confirmRemoveDialog = ConfirmRemoveDialog(
        {super.remove(removePosition, removeId)},
        context
    )

    override val removeButtonBackground = R.drawable.delete_ic

    override fun remove(position: Int, id: Long) {
        removePosition = position
        removeId = id
        confirmRemoveDialog.showWindow()
    }
}