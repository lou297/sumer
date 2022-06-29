package com.example.sumer.firebase

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        //수신한 메시지 처리
        super.onMessageReceived(message)

        val notificationManager = NotificationManagerCompat.from(
            applicationContext
        )
        val CHANNEL_ID = "45"
        val CHANNEL_NAME = "LAWBIN"

        var builder: NotificationCompat.Builder? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        } else {
            builder = NotificationCompat.Builder(applicationContext)
        }

        val title: String? = message.notification?.title
        val body:  String? = message.notification?.body

        builder!!.setContentTitle(title)
            .setContentText(body)

        val notification: Notification = builder!!.build()
        notificationManager.notify(45, notification)

    }

    override fun onNewToken(token: String) {
        //token을 서버로 전송
        super.onNewToken(token)
    }
}