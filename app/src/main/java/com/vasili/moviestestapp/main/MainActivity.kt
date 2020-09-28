package com.vasili.moviestestapp.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.vasili.moviestestapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.loading.observe(this, {
            progress.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.mainCommandStream.observe(this, {
            when (it) {
                is MainViewModel.ViewCommand.ERROR -> Snackbar.make(
                    nav_host_fragment,
                    getString(R.string.error_message),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }
}