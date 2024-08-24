package ua.sviatkuzbyt.intervalreminders.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ua.sviatkuzbyt.intervalreminders.data.elements.CardData

@Dao
interface RepeatCardDao{
    //CARDS
    @Insert
    fun addCard(entity: CardEntity): Long

    @Query("SELECT * FROM card")
    fun getCards(): MutableList<CardData>

    @Query("DELETE FROM card WHERE id=:id")
    fun removeCard(id: Long)

    //REPEAT
    @Insert
    fun addRepeat(entity: RepeatEntity)

    @Query("SELECT r.id, c.name FROM repeat r " +
            "INNER JOIN card c ON c.id = r.cardId " +
            "WHERE r.date <= :currentDay")
    fun getRepeats(currentDay: Long): MutableList<CardData>

    @Query("DELETE FROM repeat WHERE id=:repeatId")
    fun removeRepeat(repeatId: Long)
}