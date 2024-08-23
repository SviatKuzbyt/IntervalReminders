package ua.sviatkuzbyt.intervalreminders.data.repositories

import android.content.Context
import ua.sviatkuzbyt.intervalreminders.data.db.DataBaseObject
import java.time.LocalDate

class RepeatRepository(context: Context) {
    private val dao = DataBaseObject.getDao(context)

    fun load() = dao.getRepeats(LocalDate.now().toEpochDay())

    fun remove(repeatId: Long){
        dao.removeRepeat(repeatId)
    }
}