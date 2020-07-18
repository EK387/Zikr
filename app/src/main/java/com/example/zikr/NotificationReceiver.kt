package com.example.zikr

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

            val notificationHelper = NotificationHelper(context)
            val nb = notificationHelper.channelNotification
            notificationHelper.manager!!.notify(1, nb.build())
        }
    }

