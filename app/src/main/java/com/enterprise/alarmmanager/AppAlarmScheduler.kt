package com.enterprise.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat


class AppAlarmScheduler(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun createPendingIntent(reminderItem: ReminderItem): PendingIntent {
        val intent = Intent(context, AppAlarmBroadcastReceiver::class.java)

        return PendingIntent.getBroadcast(
            context,
            reminderItem.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun schedule(reminderItem: ReminderItem) {
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            reminderItem.time,
            AlarmManager.INTERVAL_DAY,
            createPendingIntent(reminderItem)
        )
    }


    //This is not repeating, to achieve repeating, schedule one more with this
    //function inside broadcast receiver thanks to that every broadcast receiver call will trigger one more schedule
    //so that we will achieve repeating behavior
    override fun scheduleExactAndAllowWhileIdle(reminderItem: ReminderItem) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            reminderItem.time,
            createPendingIntent(reminderItem)
        )
    }

    override fun requestPermission(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = ContextCompat.getSystemService(context, AlarmManager::class.java)
            if (alarmManager?.canScheduleExactAlarms() == false) {
                Intent().also { intent ->
                    intent.action = Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
                    context.startActivity(intent)
                }
            }
        }

    }

    override fun cancel(reminderItem: ReminderItem) {
        alarmManager.cancel(
            createPendingIntent(reminderItem)
        )
    }
}