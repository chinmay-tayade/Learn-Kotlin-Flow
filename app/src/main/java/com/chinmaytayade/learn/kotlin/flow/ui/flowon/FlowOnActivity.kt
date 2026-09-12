package com.chinmaytayade.learn.kotlin.flow.ui.flowon

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.chinmaytayade.learn.kotlin.flow.R
import com.chinmaytayade.learn.kotlin.flow.data.api.ApiHelperImpl
import com.chinmaytayade.learn.kotlin.flow.data.api.RetrofitBuilder
import com.chinmaytayade.learn.kotlin.flow.data.local.DatabaseBuilder
import com.chinmaytayade.learn.kotlin.flow.data.local.DatabaseHelperImpl
import com.chinmaytayade.learn.kotlin.flow.databinding.ActivityLongRunningTaskBinding
import com.chinmaytayade.learn.kotlin.flow.ui.base.ViewModelFactory
import com.chinmaytayade.learn.kotlin.flow.utils.DefaultDispatcherProvider

class FlowOnActivity : AppCompatActivity() {

    private lateinit var viewModel: FlowOnViewModel
    private lateinit var binding: ActivityLongRunningTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLongRunningTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTextView()
        setupViewModel()
        setupTask()
    }

    private fun setupTextView() {
        binding.progressBar.visibility = View.GONE
        binding.textView.text = getString(R.string.check_logcat)
        binding.textView.visibility = View.VISIBLE
    }

    private fun setupTask() {
        viewModel.startFlowOnTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)),
                DefaultDispatcherProvider()
            )
        )[FlowOnViewModel::class.java]
    }
}
