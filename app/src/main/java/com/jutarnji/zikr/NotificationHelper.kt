package com.jutarnji.zikr

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build

import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import java.util.*


class NotificationHelper(base: Context?) : ContextWrapper(base) {


    private var mManager: NotificationManager? = null
    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel = NotificationChannel(
            channelID,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        manager!!.createNotificationChannel(channel)
    }

    val manager: NotificationManager?
        get() {
            if (mManager == null) {
                mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            return mManager
        }
    val calendar: Calendar = Calendar.getInstance()
    val h = calendar.get(Calendar.HOUR_OF_DAY)

    var zikr = if (h in 2..14) {
        "| Vrijeme je za jutarnji zikr! |"
    }else {
        "| Vrijeme je za večernji zikr! |"
    }

    val activityIntent =
        if (h in 2..14) {
            Intent(this, Jutarnji::class.java)
        } else {
            Intent(this, Vecernji::class.java)
        }


    private val contentIntent: PendingIntent = PendingIntent.getActivity(this, 1, activityIntent, 0)
    val channelNotification: NotificationCompat.Builder

        get() = NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(getString(R.string.app_name))
            .setAutoCancel(true)
            .setColor(ContextCompat.getColor(this, R.color.hadisi))
            .setLights(Color.YELLOW, 3000, 3000)
            .setVibrate(longArrayOf(100, 500, 100))
            .setContentText("$zikr")
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.ic_logo_white)


    companion object {
        const val channelID = "channelID"
        const val channelName = "Channel Name"

    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
    }

}
