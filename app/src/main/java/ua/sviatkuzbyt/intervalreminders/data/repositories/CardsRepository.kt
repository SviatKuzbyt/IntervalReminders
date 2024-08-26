package ua.sviatkuzbyt.intervalreminders.data.repositories

import android.content.Context
import ua.sviatkuzbyt.intervalreminders.data.db.CardEntity
import ua.sviatkuzbyt.intervalreminders.data.db.DataBaseObject
import ua.sviatkuzbyt.intervalreminders.data.db.RepeatEntity
import ua.sviatkuzbyt.intervalreminders.data.notification.ScheduleNotification
import java.time.LocalDate

class CardsRepository(private val context: Context)  {
    private val dao = DataBaseObject.getDao(context)
    private val scheduleNotification = ScheduleNotification(context)

    fun load() = dao.getCards()

    fun remove(cardId: Long){
        dao.getRepeatsById(cardId).forEach {
            scheduleNotification.cancel(it.toInt())
        }
        dao.removeCard(cardId)
    }

    fun add(name: String): Long{
        val dao = DataBaseObject.getDao(context)
        val currencyDay = LocalDate.now()
        val cardId = dao.addCard(CardEntity(0, name))

        val dates = listOf(
            currencyDay.plusDays(1),
            currencyDay.plusDays(3),
            currencyDay.plusDays(7),
            currencyDay.plusMonths(1),
            currencyDay.plusMonths(3),
            currencyDay.plusMonths(6),
            currencyDay.plusYears(1)
        )

        dates.forEach {
            val id = dao.addRepeat(RepeatEntity(0, it.toEpochDay(), cardId))
            scheduleNotification.add(name, it, id.toInt())
        }

        return cardId
    }
}