package ua.sviatkuzbyt.intervalreminders.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "repeat",
    foreignKeys = [
        ForeignKey(
            entity = CardEntity::class,
            parentColumns = ["id"],
            childColumns = ["cardId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RepeatEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val date: Long,
    val cardId: Long
)