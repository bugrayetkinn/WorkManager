package com.yetkin.workmanager.utils

import com.yetkin.workmanager.BuildConfig

object Constants {

    // NOTIFICATION
    const val CHANNEL_NAME = BuildConfig.APPLICATION_ID
    const val ONE_TIME_WORKER_ID = 2000
    const val ONE_TIME_CHANNEL_ID = "ONE_TIME_WORKER"
    const val PERIODIC_WORKER_ID = 2001
    const val PERIODIC_CHANNEL_ID = "PERIODIC_WORKER"

    // WORK MANAGER
    const val ONE_TIME_INPUT_DATA = "input_data"
}