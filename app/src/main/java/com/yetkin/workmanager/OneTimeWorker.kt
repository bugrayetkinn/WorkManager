package com.yetkin.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.yetkin.workmanager.utils.Constants.ONE_TIME_CHANNEL_ID
import com.yetkin.workmanager.utils.Constants.ONE_TIME_WORKER_ID
import com.yetkin.workmanager.utils.NotificationProvider
import kotlinx.coroutines.delay
import kotlin.random.Random


class OneTimeWorker(private val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        NotificationProvider.showNotification(
            context,
            10000,
            ONE_TIME_WORKER_ID,
            ONE_TIME_CHANNEL_ID,
            "One Time Worker",
            "Worker",
            "Worked !"
        )

        val random = Random.nextInt(0, 2)
        for (i in 0..10) {
            delay(1000)
        }

        val data = Data.Builder().putInt("random", random).build()
        return if (random == 0) Result.success(data) else Result.failure(data)
    }
}