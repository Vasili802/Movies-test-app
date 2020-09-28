package com.vasili.moviestestapp.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline observer: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, {
        observer(it)
    })
}