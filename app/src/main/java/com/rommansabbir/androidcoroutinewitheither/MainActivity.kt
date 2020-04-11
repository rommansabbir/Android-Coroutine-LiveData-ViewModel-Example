package com.rommansabbir.androidcoroutinewitheither

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initViewModel()
    }

    /**
     * Instantiate view model
     */
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.init(this)
    }

    /**
     * Before stop activity, don't forget to call [viewModel.onDestroy()]
     */
    override fun onStop() {
        viewModel.onDestroy()
        super.onStop()
    }
}

/**
 * Extension function to Execute some work on UI Thread using [GlobalScope]
 *
 * @param body, pass the function that you want to execute on [Dispatchers.Main] or [UIThread]
 */
inline fun AppCompatActivity.executeOnUIThread(crossinline body: () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) {
        body()
    }
}



