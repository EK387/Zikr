package com.example.zikr

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmRebootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val notificationHelper = NotificationHelper(context)
            val nb = notificationHelper.channelNotification
            notificationHelper.manager!!.notify(1, nb.build())
        }
    }
}