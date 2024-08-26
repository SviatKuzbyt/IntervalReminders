package ua.sviatkuzbyt.intervalreminders.data.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.LocalDate
import java.time.ZoneId

class ScheduleNotification(private val context: Context) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun add(message: String, date: LocalDate, id: Int) {
        val intent = Intent(context, ReminderReceiver::class.java)
        intent.putExtra("message", message)
        intent.putExtra("id", id)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, convertLocalDateToMillis(date), pendingIntent)
    }

    fun cancel(id: Int) {
        val intent = Intent(context, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    private fun convertLocalDateToMillis(localDate: LocalDate): Long {
        val localDateTime = localDate.atTime(10, 0)
        val instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()
        return instant.toEpochMilli()
    }
}