package com.rommansabbir.androidcoroutinewitheither

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {
    private lateinit var activity: MainActivity
    private var scope: CoroutineContext? = null

    private var counterLiveData: MutableLiveData<MutableList<DataModel>> = MutableLiveData()

    fun init(activity: MainActivity) {
        this.activity = activity

        activity.startButton.setOnClickListener {
            fetchFakeDataFromNetwork(activity)
        }

        activity.anotherActivityBtn.setOnClickListener {
            activity.startActivity(Intent(activity, AnotherActivity::class.java))
        }

        activity.cancelButton.setOnClickListener {
            cancelScope()
        }

        observeCounterValue(activity)
    }

    /**
     * Define a [scope] instance by providing [Dispatchers.IO] plus with a [Job]
     * Start the data fetching in [Dispatchers.IO] thread by calling [CoroutineScope] [launch]
     * Get notified about the fake data fetching by using kotlin higher order function
     * Based on status, update the UI or cancel the [Job] or show error message to the UI
     *
     * @param activity, pass the [activity] reference
     */
    private fun fetchFakeDataFromNetwork(activity: MainActivity) {
        scope = Dispatchers.IO + Job()
        activity.recyclerView.visibility = View.GONE
        scope?.let { it ->
            CoroutineScope(it).launch {
                startJob(
                    {
                        showLoading(true)
                        updateUI("Job Started on IO Thread")
                    },
                    {
                        activity.executeOnUIThread {
                            counterLiveData.value = it
                        }
                        showLoading(false)
                        showMessage("Job completed")
                    },
                    {
                        showLoading(false)
                        showMessage(it.message.toString())
                        scope = null
                    }
                )

            }
        }
    }

    /**
     * Cancel the [CoroutineScope] by calling [scope.cancel()]
     * Pass a specific message as a cancellation message by instantiate an instance of [CancellationException]
     * Update UI calling [updateUI], pass a message to the UI
     */
    private fun cancelScope() {
        scope?.cancel(CancellationException("Job canceled by User"))
        updateUI("Job canceled by User")
    }

    /**
     * Observe the [counterLiveData] to update the UI based on it's value
     * Pass the [activity] reference to observe the [counterLiveData]
     * On value update, [updateUI] to to show a specific message to the UI
     * Update [recyclerView] adapter to show list of data to the UI by instantiate [Adapter] by passing [activity] context & [MutableList] of [DataModel]
     *
     * @param activity, pass the activity reference with which you want to observe the [counterLiveData]
     */
    private fun observeCounterValue(activity: MainActivity) {
        counterLiveData.observe(activity, Observer<MutableList<DataModel>> {
            setupRecyclerViews()
            updateUI("Total Data Size: ${it.size}")
            activity.recyclerView.adapter = Adapter(activity, it)
        })
    }

    /**
     * Setup recycler view for usages
     */
    private fun setupRecyclerViews() {
        activity.recyclerView.visibility = View.VISIBLE
        activity.recyclerView.setHasFixedSize(true)
        activity.recyclerView.layoutManager = LinearLayoutManager(activity)
    }


    /**
     * Start the data fetching job by calling [startJob]
     * It's a suspend function, so it can be only called from a [CoroutineScope]
     * Get notified about the job status using kotlin higher order function
     *
     * @param onSubscribe, higher order function to get notified when this function start execution in a [CoroutineScope]
     * @param onSuccess, higher order function to get notified about required data which return [MutableList] of [DataModel]
     * @param onError, higher order function to get notified about any error occurrence or job cancellation which return an instance of [CancellationException]
     */
    private suspend fun startJob(
        onSubscribe: () -> Unit,
        onSuccess: (MutableList<DataModel>) -> Unit,
        onError: (CancellationException) -> Unit
    ) {
        onSubscribe.invoke()
        try {
            showMessage(Thread.currentThread().name)
            delay(1000)
            val data = DataModelProvider.getModels()
            onSuccess.invoke(data)
        } catch (e: CancellationException) {
            onError.invoke(e)
        }
    }

    /**
     * Show a passed message to the [dataList] text view
     * call [executeOnUIThread] extension function to execute the work on UI Thread
     *
     * @param message, value to show or hide loading screen in [Boolean] format
     */
    private fun updateUI(message: String) {
        activity.executeOnUIThread {
            activity.dataList.text = message
        }
    }

    /**
     * Show/Hide loading screen based on [value]
     * call [executeOnUIThread] extension function to execute the work on UI Thread
     *
     * @param value, pass the message in [String] format
     */
    private fun showLoading(value: Boolean) {
        activity.executeOnUIThread {
            with(activity.progressBar) {
                visibility = if (value) View.VISIBLE else View.GONE
            }
        }
    }

    /**
     * Show a message to the UI
     *
     * @param message, pass the message in [String] format
     */
    private fun showMessage(message: String) {
        activity.executeOnUIThread {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Make sure to call this method to cancel current job which is executed under this scope
     */
    fun onDestroy() {
        scope?.cancel(CancellationException("On Destroy called"))
    }
}


