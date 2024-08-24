package ua.sviatkuzbyt.intervalreminders.data.repositories

import android.content.Context
import ua.sviatkuzbyt.intervalreminders.data.db.CardEntity
import ua.sviatkuzbyt.intervalreminders.data.db.DataBaseObject
import ua.sviatkuzbyt.intervalreminders.data.db.RepeatEntity
import java.time.LocalDate

class CardsRepository(private val context: Context)  {
    private val dao = DataBaseObject.getDao(context)

    fun load() = dao.getCards()

    fun remove(cardId: Long){
        dao.removeCard(cardId)
    }

    fun add(name: String): Long{
        val dao = DataBaseObject.getDao(context)
        val currencyDay = LocalDate.now()
        val cardId = dao.addCard(CardEntity(0, name))

        val dates = listOf(
            currencyDay.plusDays(1).toEpochDay(),
            currencyDay.plusDays(7).toEpochDay(),
            currencyDay.plusMonths(1).toEpochDay(),
            currencyDay.plusMonths(3).toEpochDay(),
            currencyDay.plusMonths(6).toEpochDay(),
            currencyDay.plusYears(1).toEpochDay()
        )

        dates.forEach {
            dao.addRepeat(RepeatEntity(0, it, cardId))
        }

        return cardId
    }
}