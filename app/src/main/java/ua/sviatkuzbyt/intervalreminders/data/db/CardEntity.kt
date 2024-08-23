package ua.sviatkuzbyt.intervalreminders.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val cardId: Long,
    val name: String
)
