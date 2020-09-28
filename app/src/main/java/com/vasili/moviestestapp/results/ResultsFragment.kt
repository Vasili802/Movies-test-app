package com.vasili.moviestestapp.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.vasili.moviestestapp.R
import com.vasili.moviestestapp.common.Video
import com.vasili.moviestestapp.main.MainViewModel
import com.vasili.moviestestapp.common.observe
import kotlinx.android.synthetic.main.fragment_results.*

class ResultsFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val values = mutableListOf<Video>()
        val adapter = VideoAdapter(values)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        observe(viewModel.results) {
            values.clear()
            values.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }
}