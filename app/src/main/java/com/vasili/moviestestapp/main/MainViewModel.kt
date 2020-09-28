package com.vasili.moviestestapp.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasili.moviestestapp.common.Repository
import com.vasili.moviestestapp.common.SearchRequest
import com.vasili.moviestestapp.common.SingleLiveEvent
import com.vasili.moviestestapp.common.Video
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
) : ViewModel() {
    val results = MutableLiveData<List<Video>>()
    val mainCommandStream = SingleLiveEvent<ViewCommand>()
    val searchCommandStream = SingleLiveEvent<ViewCommand>()
    val loading = MutableLiveData(false)
    val previousSearches: LiveData<List<SearchRequest>> by lazy {
        repository.getSearches()
    }

    fun onSearchClick(searchRequest: SearchRequest) {
        viewModelScope.launch {
            loading.value = true
            try {
                results.value =
                    repository.getVideos(searchRequest).search ?: throw Exception("not found")
                searchCommandStream.value = ViewCommand.NavigateToResults
                repository.saveSearch(searchRequest)
                loading.value = false
            } catch (e: Exception) {
                loading.value = false
                mainCommandStream.value =
                    ViewCommand.ERROR
            }
        }
    }

    sealed class ViewCommand {
        object NavigateToResults : ViewCommand()
        object ERROR : ViewCommand()
    }
}