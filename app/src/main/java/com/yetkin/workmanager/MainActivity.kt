package com.yetkin.workmanager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.yetkin.workmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activity = this
    }

    fun oneTimeWorkRequest() {
        val TAG = "Work Manager"
        val data = Data.Builder().putString("userName", "Buğra").build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest =
            OneTimeWorkRequest.Builder(OneTimeWorker::class.java)
                .setInitialDelay(20, TimeUnit.SECONDS)
                .setInputData(data)
                .setConstraints(constraints)
                .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(workRequest)

        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this) { workInfo ->
            when (workInfo.state) {
                WorkInfo.State.ENQUEUED -> Log.e(TAG, "ENQUEUED")
                WorkInfo.State.RUNNING -> Log.e(TAG, "RUNNING")
                WorkInfo.State.SUCCEEDED -> Log.e(TAG, "SUCCEEDED - ${workInfo.outputData}")
                WorkInfo.State.FAILED -> Log.e(TAG, "FAILED - ${workInfo.outputData}")
                WorkInfo.State.BLOCKED -> Log.e(TAG, "BLOCKED")
                WorkInfo.State.CANCELLED -> Log.e(TAG, "CANCELLED")
            }
        }
    }

    fun periodicWorkRequest() {

        val workRequest =
            PeriodicWorkRequest.Builder(
                PeriodicWorker::class.java,
                15,
                TimeUnit.MINUTES
            ).build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}