package com.enterprise.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class AppAlarmBroadcastReceiver : BroadcastReceiver() {

    val TAG = "AppAlarmBroadcastReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d(TAG, "Alarm Manager runs")

    }

}