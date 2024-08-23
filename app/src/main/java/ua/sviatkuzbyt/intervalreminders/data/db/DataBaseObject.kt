package ua.sviatkuzbyt.intervalreminders.data.db

import android.content.Context
import androidx.room.Room

object DataBaseObject {
    private var dataBase: RepeatCardRoom? = null
    fun getDao(context: Context) = getDatabase(context).dao()

    private fun getDatabase(context: Context): RepeatCardRoom {
        if (dataBase == null) {
            synchronized(DataBaseObject::class.java) {
                if (dataBase == null) {
                    dataBase = Room.databaseBuilder(
                        context.applicationContext,
                        RepeatCardRoom::class.java,
                        "repeat_card_db"
                    ).build()
                }
            }
        }
        return dataBase!!
    }
}