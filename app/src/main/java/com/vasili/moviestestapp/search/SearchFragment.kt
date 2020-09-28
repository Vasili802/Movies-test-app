package com.vasili.moviestestapp.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.vasili.moviestestapp.R
import com.vasili.moviestestapp.common.SearchRequest
import com.vasili.moviestestapp.common.observe
import com.vasili.moviestestapp.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_search.*


private const val TITLE_ARG = "TITLE_ARG"
private const val YEAR_ARG = "YEAR_ARG"
private const val MOVIE_ARG = "MOVIE_ARG"

class SearchFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            title.editText?.setText(savedInstanceState.getString(TITLE_ARG).orEmpty())
            year.editText?.setText(savedInstanceState.getString(YEAR_ARG).orEmpty())
            type.setSelection(savedInstanceState.getInt(MOVIE_ARG).or(0))
        }
        searchButton.setOnClickListener {
            closeKeyBoard()
            viewModel.onSearchClick(
                SearchRequest(
                    title = title.editText?.text.toString(),
                    year = year.editText?.text.toString(),
                    type = type.selectedItem.toString()
                )
            )
        }
        previousButton.setOnClickListener {
            findNavController().navigate(R.id.fragmentPreviousSearches)
        }
        observe(viewModel.searchCommandStream) {
            when (it) {
                MainViewModel.ViewCommand.NavigateToResults -> findNavController().navigate(R.id.fragmentResults)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TITLE_ARG, title?.editText?.text?.toString().orEmpty())
        outState.putString(YEAR_ARG, year?.editText?.text?.toString().orEmpty())
        outState.putInt(MOVIE_ARG, type?.selectedItemPosition ?: 0)
    }

    private fun closeKeyBoard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

}