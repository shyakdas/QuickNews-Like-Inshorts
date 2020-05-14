package com.quicknews.viewmodel.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

/**
 * BaseViewModel(application) will contain all the common function of which can be used in all the
 * ViewModel of the application. Those method which can be called from any of the ViewModel in
 * Quick news should be placed here
 */

class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private var isOnBackPressed: MutableLiveData<Boolean>? = null

    init {
        isOnBackPressed = MutableLiveData()
    }

    private fun cancelAllRequests() = viewModelScope.coroutineContext.cancel()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }
}