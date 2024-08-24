package ua.sviatkuzbyt.intervalreminders.ui.elements.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.data.elements.CardData
import ua.sviatkuzbyt.intervalreminders.ui.elements.interfaces.RecyclerAction

open class RemindAdapter(
    protected val dataSet: MutableList<CardData>,
    private val action: RecyclerAction
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected open val removeButtonBackground = R.drawable.done_ic
    protected open val emptyTextId = R.string.no_repeats
    protected open val emptyIconId = R.drawable.done_big_ic

    private val EMPTY_VIEW = 0
    private val CONTENT_VIEW = 1

    inner class EmptyStateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val emptyItemText = view.findViewById<TextView>(R.id.emptyItemText)
        private val emptyItemIcon = view.findViewById<View>(R.id.emptyItemIcon)

        fun bind(){
            emptyItemText.setText(emptyTextId)
            emptyItemIcon.setBackgroundResource(emptyIconId)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val text = view.findViewById<TextView>(R.id.recyclerItemText)
        private val removeButton = view.findViewById<Button>(R.id.recyclerItemButton)

         fun bind(data: CardData){
            removeButton.setBackgroundResource(removeButtonBackground)
            text.text = data.name

            removeButton.setOnClickListener {
                remove(adapterPosition, data.id)
            }
        }
    }

    open fun remove(position: Int, id: Long){
        dataSet.removeAt(position)
        notifyItemRemoved(position)
        action.removeAction(id)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == CONTENT_VIEW){
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_item, viewGroup, false)
            ViewHolder(view)
        } else {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.empty_item, viewGroup, false)
            EmptyStateViewHolder(view)
        }

    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if(viewHolder is ViewHolder)
            viewHolder.bind(dataSet[position])
        else if(viewHolder is EmptyStateViewHolder)
            viewHolder.bind()
    }

    override fun getItemCount(): Int {
        return if (dataSet.isEmpty()) 1 else dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataSet.isEmpty()) EMPTY_VIEW else CONTENT_VIEW
    }
}
