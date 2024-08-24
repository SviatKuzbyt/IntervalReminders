package ua.sviatkuzbyt.intervalreminders.data.repositories

import android.content.Context
import ua.sviatkuzbyt.intervalreminders.data.db.DataBaseObject

class CardsRepository(context: Context)  {
    private val dao = DataBaseObject.getDao(context)

    fun load() = dao.getCards()

    fun remove(cardId: Long){
        dao.removeCard(cardId)
    }
}