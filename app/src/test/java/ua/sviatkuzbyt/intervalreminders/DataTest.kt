package ua.sviatkuzbyt.intervalreminders

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class DataTest {

    @Test
    fun testAddDays(){
        val addDaysCount = 7L
        val currentDate = LocalDate.now()
        val daysLater = currentDate.plusDays(addDaysCount)
        val currentDateNum = currentDate.toEpochDay()
        val daysLaterNum = daysLater.toEpochDay()

        Assert.assertEquals(currentDateNum+addDaysCount, daysLaterNum)
    }
}