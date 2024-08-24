package ua.sviatkuzbyt.intervalreminders

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ua.sviatkuzbyt.intervalreminders.data.db.CardEntity
import ua.sviatkuzbyt.intervalreminders.data.db.DataBaseObject
import ua.sviatkuzbyt.intervalreminders.data.db.RepeatCardDao
import ua.sviatkuzbyt.intervalreminders.data.db.RepeatEntity
import java.time.LocalDate

class DataBaseTest {

    private lateinit var dbDao: RepeatCardDao

    @Before
    fun setup(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        dbDao = DataBaseObject.getDao(context)
    }

    @Test
    fun addCards(){
        val addName = "card1"
        dbDao.addCard(CardEntity(0, addName))
        val listResult = dbDao.getCards()
        Assert.assertTrue(listResult.any { it.name == addName })
    }

    @Test
    fun removeCards(){
        val removeId = dbDao.getCards()[0].id
        dbDao.removeCard(removeId)
        val listResult = dbDao.getCards()
        Assert.assertFalse(listResult.any { it.id == removeId })
    }

    @Test
    fun addRepeat(){
        val card = dbDao.addCard(CardEntity(0, "card2"))
        val dates = listOf(
            LocalDate.of(2024, 8, 10).toEpochDay(), //current
            LocalDate.of(2024, 8, 1).toEpochDay(),
            LocalDate.of(2024, 8, 20).toEpochDay()
        )

        dates.forEach {
            dbDao.addRepeat(RepeatEntity(0, it, card))
        }

        val listResult = dbDao.getRepeats(dates[0])
        Assert.assertEquals(2, listResult.count())
    }
}