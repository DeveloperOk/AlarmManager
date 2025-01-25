package com.enterprise.alarmmanager

import android.app.PendingIntent
import android.content.Context


interface AlarmScheduler {
    fun createPendingIntent(reminderItem: ReminderItem): PendingIntent

    fun schedule(reminderItem: ReminderItem)

    fun scheduleExactAndAllowWhileIdle(reminderItem: ReminderItem)

    fun requestPermission(context: Context)

    fun cancel(reminderItem: ReminderItem)
}