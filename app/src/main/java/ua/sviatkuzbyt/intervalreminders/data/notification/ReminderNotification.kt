package ua.sviatkuzbyt.intervalreminders.data.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import ua.sviatkuzbyt.intervalreminders.R
import ua.sviatkuzbyt.intervalreminders.ui.MainActivity

class ReminderNotification(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    //intent for opening app from notification
    private val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }
    private val openActivity = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    //build and shoe notification
    fun sendReminderNotification(message: String?, id: Int) {
        val notification = NotificationCompat.Builder(context, "1")
            .setContentText(context.getString(R.string.repeat_card))
            .setContentTitle(message)
            .setSmallIcon(R.drawable.icon_notification)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .setContentIntent(openActivity)
            .build()

        notificationManager.notify(id, notification)
    }
}