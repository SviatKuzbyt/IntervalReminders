package ua.sviatkuzbyt.intervalreminders.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ua.sviatkuzbyt.intervalreminders.data.elements.RepeatData

@Dao
interface RepeatCardDao{
    //CARDS
    @Insert
    fun addCard(entity: CardEntity): Long

    @Query("SELECT * FROM card")
    fun getCards(): MutableList<CardEntity>

    @Query("DELETE FROM card WHERE cardId=:cardId")
    fun removeCard(cardId: Long)

    //REPEAT
    @Insert
    fun addRepeat(entity: RepeatEntity)

    @Query("SELECT r.repeatId, c.name FROM repeat r " +
            "INNER JOIN card c ON c.cardId = r.cardId " +
            "WHERE r.date <= :currentDay")
    fun getRepeats(currentDay: Long): MutableList<RepeatData>

    @Query("DELETE FROM repeat WHERE repeatId=:repeatId")
    fun removeRepeat(repeatId: Long)
}