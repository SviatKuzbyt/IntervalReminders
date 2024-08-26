package ua.sviatkuzbyt.intervalreminders.data.repositories

import android.content.Context
import ua.sviatkuzbyt.intervalreminders.data.db.CardEntity
import ua.sviatkuzbyt.intervalreminders.data.db.DataBaseObject
import ua.sviatkuzbyt.intervalreminders.data.db.RepeatEntity
import ua.sviatkuzbyt.intervalreminders.data.notification.ScheduleNotification
import java.time.LocalDate

class CardsRepository(context: Context)  {
    private val dao = DataBaseObject.getDao(context)
    private val scheduleNotification = ScheduleNotification(context)

    fun load() = dao.getCards()

    fun add(name: String): Long{
        val currencyDay = LocalDate.now()
        val cardId = dao.addCard(CardEntity(0, name)) //add card to DB

        val dates = listOf(
            currencyDay.plusDays(1),
            currencyDay.plusDays(3),
            currencyDay.plusDays(7),
            currencyDay.plusMonths(1),
            currencyDay.plusMonths(3),
            currencyDay.plusMonths(6),
            currencyDay.plusYears(1)
        )

        //add repeats to DB and notifications
        dates.forEach {
            val id = dao.addRepeat(RepeatEntity(0, it.toEpochDay(), cardId))
            scheduleNotification.add(name, it, id.toInt())
        }

        return cardId
    }

    fun remove(cardId: Long){
        //cancel notifications
        dao.getRepeatsById(cardId).forEach {
            scheduleNotification.cancel(it.toInt())
        }
        //remove from DB
        dao.removeCard(cardId)
    }
}