package ua.sviatkuzbyt.intervalreminders.data.repositories

import android.content.Context
import ua.sviatkuzbyt.intervalreminders.data.db.DataBaseObject
import ua.sviatkuzbyt.intervalreminders.data.notification.ScheduleNotification
import java.time.LocalDate

class RepeatRepository(context: Context) {
    private val dao = DataBaseObject.getDao(context)
    private val scheduleNotification = ScheduleNotification(context)

    fun load() = dao.getRepeats(LocalDate.now().toEpochDay())

    fun remove(repeatId: Long){
        dao.removeRepeat(repeatId) //cancel notifications
        scheduleNotification.cancel(repeatId.toInt()) //remove from DB
    }
}