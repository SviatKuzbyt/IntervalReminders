package ua.sviatkuzbyt.intervalreminders.data.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class ScheduleNotificationApplication: Application() {

    //create notification channel
    override fun onCreate() {
        super.onCreate()
        val notificationChannel = NotificationChannel(
            "1",
            "Remind",
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}