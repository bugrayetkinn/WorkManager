package com.yetkin.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.yetkin.workmanager.utils.Constants.PERIODIC_CHANNEL_ID
import com.yetkin.workmanager.utils.Constants.PERIODIC_WORKER_ID
import com.yetkin.workmanager.utils.NotificationProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PeriodicWorker(private val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    private val TAG = "RepeatWorker"

    override suspend fun doWork(): Result {
        Log.e(TAG, "do work !")
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            NotificationProvider.showNotification(
                context = context,
                5000,
                PERIODIC_WORKER_ID,
                PERIODIC_CHANNEL_ID,
                "Worker",
                "Periodic Worker worked",
                "Worked !"
            )
        }
        return Result.success()
    }
}