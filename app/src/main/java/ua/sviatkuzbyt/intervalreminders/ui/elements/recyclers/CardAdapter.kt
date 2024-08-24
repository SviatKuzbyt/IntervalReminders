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

class CardAdapter(
    private val dataSet: MutableList<CardData>,
    private val removeButtonBackground: Int,
    private val action: RecyclerAction
) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val text = view.findViewById<TextView>(R.id.recyclerItemText)
        private val removeButton = view.findViewById<Button>(R.id.recyclerItemButton)

        fun bind(data: CardData){
            removeButton.setBackgroundResource(removeButtonBackground)
            text.text = data.name

            removeButton.setOnClickListener {
                val position = adapterPosition
                dataSet.removeAt(position)
                notifyItemRemoved(position)
                action.removeAction(data.id)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}
