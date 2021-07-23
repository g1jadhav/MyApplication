package com.temporary.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.temporary.myapplication.network.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class RocketListVMFactory @Inject constructor(val mApiInterface: ApiInterface) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RocketListViewModel::class.java))
            return RocketListViewModel(mApiInterface) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
