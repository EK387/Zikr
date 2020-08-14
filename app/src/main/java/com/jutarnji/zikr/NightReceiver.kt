package com.jutarnji.zikr

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NightReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

            val notificationHelper = NotificationHelper(context)
            val nb = notificationHelper.channelNotification
            notificationHelper.manager!!.notify(1, nb.build())
        }
    }

