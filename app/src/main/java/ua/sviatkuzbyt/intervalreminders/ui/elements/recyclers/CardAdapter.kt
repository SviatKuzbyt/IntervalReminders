package ua.sviatkuzbyt.intervalreminders.ui.elements.recyclers

import android.content.Context
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.data.CardData
import ua.sviatkuzbyt.intervalreminders.ui.elements.ConfirmRemoveDialog
import ua.sviatkuzbyt.intervalreminders.ui.elements.RecyclerAction

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
    override val emptyTextId = R.string.no_cards
    override val emptyIconId = R.drawable.add_big_ic

    override fun remove(position: Int, id: Long) {
        removePosition = position
        removeId = id
        confirmRemoveDialog.showWindow()
    }

    fun add(){
        if (dataSet.size <= 1)
            notifyItemRemoved(0)
        notifyItemInserted(0)
    }
}