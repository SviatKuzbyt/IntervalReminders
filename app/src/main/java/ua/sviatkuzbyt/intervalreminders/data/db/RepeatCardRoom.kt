package ua.sviatkuzbyt.intervalreminders.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        RepeatEntity::class,
        CardEntity::class
               ],
    version = 1,
    exportSchema = false
)

abstract class RepeatCardRoom: RoomDatabase() {
    abstract fun dao(): RepeatCardDao
}