package ua.sviatkuzbyt.intervalreminders.data.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val scheduleNotificationService = context?.let { ReminderNotification(it) }
        val message = intent?.getStringExtra("message")
        val id = intent?.getIntExtra("id", 1) ?: 1
        scheduleNotificationService?.sendReminderNotification(message, id)
    }
}