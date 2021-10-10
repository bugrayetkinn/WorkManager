package com.yetkin.workmanager.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.yetkin.workmanager.MainActivity
import com.yetkin.workmanager.R

object NotificationProvider {

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(channelId: String, notificationManager: NotificationManager) {
        val notificationChannel =
            NotificationChannel(
                channelId,
                Constants.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
        val isExistChannel = notificationManager.getNotificationChannel(channelId)
        if (isExistChannel == null) {
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun showNotification(
        context: Context,
        requestCode: Int,
        notificationId:Int,
        channelId: String,
        contentTitle: String,
        subText: String,
        contentText: String
    ) {
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
            setAutoCancel(true)
            setContentTitle(contentTitle)
            setSubText(subText)
            setContentIntent(pendingIntent)
            setContentText(contentText)
            setSmallIcon(R.drawable.ic_star)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(channelId, notificationManager)
        }

        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}