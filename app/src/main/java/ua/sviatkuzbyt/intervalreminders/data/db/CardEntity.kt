package ua.sviatkuzbyt.intervalreminders.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String
)
